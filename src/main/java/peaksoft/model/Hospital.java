package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


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
    @Column(unique = true)
    @NotEmpty(message = "Аты бош болбошу керек!")
    private String name;
    @NotEmpty(message =  "Адреси бош болбошу керек!")
    @Column(unique = true)
    private String address;
    @OneToMany(mappedBy = "hospital",cascade = ALL,fetch = FetchType.EAGER)
    private List<Doctor>doctors = new ArrayList<>();
    @OneToMany(mappedBy = "hospital",cascade = ALL,fetch = FetchType.EAGER)
    private List<Patient>patients = new ArrayList<>();
    @OneToMany(mappedBy = "hospital",cascade = ALL, fetch = FetchType.EAGER)
    private List<Department>departments = new ArrayList<>();
    @OneToMany(cascade = ALL,fetch = FetchType.EAGER)
    private List<Appointment>appointments = new ArrayList<>();
    public void addAppointment(Appointment appointment){
        if (appointments== null){
           appointments= new ArrayList<>();
        } appointments.add(appointment);

    }

    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
