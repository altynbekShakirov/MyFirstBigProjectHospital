package hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    @SequenceGenerator(name = "appointment_id_gen",sequenceName = "appointment_id_seq",allocationSize = 1)
    private Long id;
    private LocalDate date;

    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private Patients patients;
    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private Doctor doctor;
    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private  Department department;
    @ManyToOne(cascade = {REFRESH,DETACH,MERGE,PERSIST},fetch = FetchType.LAZY)
    private Hospital hospital;



}
