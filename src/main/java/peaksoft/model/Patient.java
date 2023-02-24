package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.intellij.lang.annotations.Pattern;

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
public class Patient {//бейтаптар
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patients_id_gen")
 @SequenceGenerator(name = "patients_id_gen",sequenceName = "patients_id_seq",allocationSize = 1)
 private  Long id;
 @Column(name = "first_name")
     private String firstName;
 @Column(name = "last_name")
     private String lastName;
    @Column(name = "phone_number",unique = true)
     private String phoneNumber;
     private String gender;

     @Column(unique = true)
     private String email;
     @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST})
     private Hospital hospital;

    @OneToMany(mappedBy = "patients",cascade = ALL)
     private List<Appointment> appointments;
    @Transient
    private Long hospitalId;

    public Patient(String firstName, String lastName, String phoneNumber, String gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
    }
}
