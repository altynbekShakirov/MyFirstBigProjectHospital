package hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

/**
 * The golden boy
 */
@Entity
@Table(name = "hospitals")
@NoArgsConstructor
@Getter
@Setter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hospital_id_gen")
    @SequenceGenerator(name = "hospital_id_gen",sequenceName = "hospital_seq",allocationSize = 1)
    private  Long id ;
    private String name;
    private String address;
    @OneToMany(mappedBy = "hospital",cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private List<Doctor>doctors;
    @OneToMany(mappedBy = "hospital",cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private List<Patients>patients;
    @OneToMany(mappedBy = "hospital",cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private List<Department>departments;
    @OneToMany(mappedBy = "hospital",cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private List<Appointment>appointments;
}
