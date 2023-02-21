package hospital.repostitory.repositoryImpl;

import hospital.model.Appointment;
import hospital.model.Hospital;
import hospital.repostitory.AppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The golden boy
 */
@Repository
@Controller
@Transactional

public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public AppointmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public String save(Long hospitalId, Appointment appointment) {
        entityManager.persist(appointment);
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        appointment.setHospital(hospital);
        return "Successfully saved";
    }

    @Override
    public List<Appointment> getAll() {
        return entityManager.createQuery("select l from  Appointment l ",Appointment.class).getResultList();
    }

    @Override
    public void update( Appointment newAppointment) {
        entityManager.merge(newAppointment);


    }

    @Override
    public void delete(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        entityManager.remove(appointment);
    }
}
