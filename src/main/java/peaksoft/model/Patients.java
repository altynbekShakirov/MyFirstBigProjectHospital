package hospital.model;

import hospital.model.enums.Gender;
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
@Table(name = "patients")
@NoArgsConstructor
@Getter
@Setter
public class Patients {//бейтаптар
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patients_id_gen")
 @SequenceGenerator(name = "patients_id_gen",sequenceName = "patients_id_seq",allocationSize = 1)
 private  Long id;
 @Column(name = "first_name")
     private String firstName;
 @Column(name = "last_name")
     private String lastName;

     private String phoneNumber;
     private Gender gender;

     private String email;
     @ManyToOne(cascade = ALL,fetch = FetchType.LAZY)
     private Hospital hospital;

    @OneToMany(mappedBy = "patients",cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
     private List<Appointment> appointments;

}
