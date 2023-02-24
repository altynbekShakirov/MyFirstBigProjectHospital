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
        entityManager.merge(appointment);
        return "Successfully saved";
    }

    @Override
    public Appointment getById(Long id) {
        return entityManager.find(Appointment.class,id);
    }

    @Override
    public List<Appointment> getAll(Long id) {
        return entityManager.createQuery("select a from  Hospital l join l.appointments a where l.id=:id",Appointment.class).setParameter("id",id).getResultList();
    }

    @Override
    public void update( Long id,Appointment newAppointment) {
        Appointment oldAppointment = entityManager.find(Appointment.class, id);
        oldAppointment.setDate(newAppointment.getDate());
        oldAppointment.setDepartment(entityManager.find(Department.class,newAppointment.getDepartmentId()));
        oldAppointment.setDoctor(entityManager.find(Doctor.class,newAppointment.getDoctorId()));
        oldAppointment.setPatients(entityManager.find(Patient.class,newAppointment.getPatientId()));

    }


    @Override
    public void delete(Long id) {
        try {
            List<Hospital> hospitals = entityManager.createQuery("select h from Hospital h ", Hospital.class).getResultList();
            hospitals.forEach(s -> s.getAppointments().removeIf(a -> a.getId().equals(id)));
            entityManager.remove(entityManager.find(Appointment.class, id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
