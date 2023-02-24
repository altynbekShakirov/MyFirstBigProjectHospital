package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.myExceptions.UniqueException;


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

    private String  email;
    @ManyToMany(cascade = {REFRESH,DETACH,MERGE,PERSIST})
    private List<Department>departments ;
    public void addDepartment(Department department) throws UniqueException{
        if (departments == null){
            departments = new ArrayList<>();
        }
        departments.add(department);
    }
    @OneToMany(mappedBy = "doctor",cascade = ALL)
    private List<Appointment>appointments;
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
