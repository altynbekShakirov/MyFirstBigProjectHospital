package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


/**
 * The golden boy
 */
@Entity
@Table(name = "departments")
@NoArgsConstructor
@Getter
@Setter
public class Department {//Бөлүм
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_id_gen")
    @SequenceGenerator(name = "department_id_gen",sequenceName = "department_id_seq",allocationSize = 1)
    private Long id ;
    @Column(unique = true)
    private String name ;
    @ManyToMany(mappedBy = "departments",cascade = {PERSIST,MERGE,DETACH,REFRESH},fetch = FetchType.LAZY)
    private List<Doctor>doctors;

    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private Hospital hospital;
    @Transient
    private  Long hospitalId;

    public Department(String name) {
        this.name = name;
    }

    public void addDoctor(Doctor doctor) {
        doctors=new ArrayList<>();
        doctors.add(doctor);
    }
}
