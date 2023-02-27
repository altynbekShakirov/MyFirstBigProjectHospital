package peaksoft.repostitory.repositoryImpl;

import peaksoft.model.*;
import peaksoft.repostitory.AppointmentRepository;
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
    public String save(Appointment appointment) {
        try {


            entityManager.merge(appointment);
            return "Successfully saved";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @Override
    public Appointment getById(Long id) {
        try {


            return entityManager.find(Appointment.class, id);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return  null;

    }

    @Override
    public List<Appointment> getAll(Long id) {
        try {
            return entityManager.createQuery("select a from  Hospital l join l.appointments a where l.id=:id order by a.date desc ", Appointment.class).setParameter("id", id).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        try {


            Appointment oldAppointment = entityManager.find(Appointment.class, id);
            oldAppointment.setDate(newAppointment.getDate());
            oldAppointment.setDepartment(entityManager.find(Department.class, newAppointment.getDepartmentId()));
            oldAppointment.setDoctor(entityManager.find(Doctor.class, newAppointment.getDoctorId()));
            oldAppointment.setPatients(entityManager.find(Patient.class, newAppointment.getPatientId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    @Transactional
    public void delete(Long id) {
        try {


            entityManager.remove(entityManager.find(Appointment.class, id));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());

        }
    }
}
