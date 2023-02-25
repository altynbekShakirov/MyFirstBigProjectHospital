package peaksoft.repostitory.repositoryImpl;

import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repostitory.DoctorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * The golden boy
 */
@Repository
@Controller
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private  final EntityManager entityManager;

    @Autowired
    public DoctorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String save(Long hospitalId, Doctor doctor) {
        entityManager.persist(doctor);
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        doctor.setHospital(hospital);
        return "Successfully save!!!!";
    }



    @Override
    public List<Doctor> getAll(Long id) {
        return entityManager.createQuery("select c from  Doctor c join c.hospital h where h.id=:id",Doctor.class).setParameter("id",id).getResultList();
    }



    @Override
    public Doctor getById(Long id) {
        return entityManager.find(Doctor.class,id);
    }

    @Override
    public void update( Long id,Doctor newDoctor) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setEmail(newDoctor.getEmail());
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setLastName(newDoctor.getLastName());
    }

    @Override
    public void delete(Long id) {

        entityManager.createQuery("delete  from  Doctor d where  d.id=:id").setParameter("id",id).executeUpdate();

    }
}
