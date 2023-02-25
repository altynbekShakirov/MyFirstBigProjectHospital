package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.myExceptions.UniqueException;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

/**
 * The golden boy
 */
@Entity
@Table(name = "doctors")
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctor_id_gen")
    @SequenceGenerator(name = "doctor_id_gen",sequenceName = "doctor_id_seq",allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;

    @NotBlank
    @Email(message = "Invalid email address")
    private String  email;
    @ManyToMany(cascade = {REFRESH,DETACH,MERGE,PERSIST})
    private List<Department>departments = new ArrayList<>();
    public void addDepartment(Department department) {
        if (departments == null){
            departments = new ArrayList<>();
        }
        departments.add(department);
    }
    @OneToMany(mappedBy = "doctor",cascade = ALL,fetch = FetchType.EAGER)
    private List<Appointment>appointments = new ArrayList<>();
    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST})
    private Hospital hospital;
    @Transient
    private List<Long >departmentIdes=new ArrayList<>();

    public Doctor(String firstName, String lastName, String position, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
    }
}
