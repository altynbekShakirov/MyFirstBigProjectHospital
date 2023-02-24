package peaksoft.repostitory.repositoryImpl;

import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repostitory.PatientsRepository;
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
public class PatientsRepositoryImpl implements PatientsRepository {
    @PersistenceContext
    public  final EntityManager entityManager;

    @Autowired
    public PatientsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String save(Long hospitalId, Patient patients) {
        entityManager.persist(patients);
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        patients.setHospital(hospital);
        return "Successfully saved!!";
    }


    @Override
    public List<Patient> getAll(Long id ) {
        return entityManager.createQuery("select l from  Patient l join  l.hospital s where s.id=:id", Patient.class).setParameter("id",id).getResultList();
    }

    @Override
    public Patient getById(Long id) {
        return entityManager.find(Patient.class,id);
    }

    @Override
    public void updatePatients(Long id, Patient newPatient) {
        Patient patients = entityManager.find(Patient.class, id);
        patients.setGender(newPatient.getGender());
        patients.setEmail(newPatient.getEmail());
        patients.setFirstName(newPatient.getFirstName());
        patients.setLastName(newPatient.getLastName());
        patients.setPhoneNumber(newPatient.getPhoneNumber());

    }

    @Override
    public void deleteByPatientsId(Long id) {
       entityManager.createQuery("delete from Patient  p where p.id=:id",Patient.class).setParameter("id",id).executeUpdate();
    }
}
