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
    @OneToMany(mappedBy = "hospital",cascade = ALL,fetch = FetchType.EAGER)
    private List<Doctor>doctors;
    @OneToMany(mappedBy = "hospital",cascade = ALL,fetch = FetchType.EAGER)
    private List<Patient>patients;
    @OneToMany(mappedBy = "hospital",cascade = ALL)
    private List<Department>departments;
    @OneToMany(cascade = ALL,fetch = FetchType.LAZY)
    private List<Appointment>appointments;
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
