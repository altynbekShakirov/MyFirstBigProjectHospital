package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

/**
 * The golden boy
 */
@Entity
@Table(name = "appointments")
@NoArgsConstructor
@Getter
@Setter
public class Appointment {//дайындоо
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointment_id_gen")
    @SequenceGenerator(name = "appointment_id_gen",allocationSize = 1)
    private Long id;
    private LocalDate date;

    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST})
    private Patient patients;
    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST})
    private Doctor doctor;
    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST})
    private Department department;
    @Transient
     private  Long patientId;
    @Transient
     private Long doctorId;
    @Transient
     private Long departmentId;


    public Appointment(LocalDate date) {
        this.date = date;
    }
}
