package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Pattern;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_id_gen")
    @SequenceGenerator(name = "patients_id_gen", sequenceName = "patients_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    @NotEmpty(message = "First name must not be empty!")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last name must not be empty!")
    private String lastName;
    @Column(name = "phone_number", unique = true)
    @Pattern(regexp = "^\\+996\\d{3} \\d{3} \\d{3} $", message = "The phone number must be 12 digits long and start with +996 !!!")
    private String phoneNumber;
    private String gender;

    @NotBlank
    @Email(message = "Invalid email address")
    private String email;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST})
    private Hospital hospital;

    @OneToMany(mappedBy = "patients", cascade = ALL, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();
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
