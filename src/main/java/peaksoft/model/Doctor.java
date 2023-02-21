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
    @ManyToMany(cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private List<Department>departments;
    @OneToMany(mappedBy = "doctor",cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private List<Appointment>appointments;
    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private Hospital hospital;
}
