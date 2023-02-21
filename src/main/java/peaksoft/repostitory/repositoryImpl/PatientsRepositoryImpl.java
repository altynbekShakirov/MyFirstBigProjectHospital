package hospital.repostitory.repositoryImpl;

import hospital.model.Hospital;
import hospital.model.Patients;
import hospital.repostitory.PatientsRepository;
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
    public String save(Long hospitalId, Patients patients) {
        entityManager.persist(patients);

        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        patients.setHospital(hospital);
        return "Successfully saved!!";
    }


    @Override
    public List<Patients> getAll() {
        return entityManager.createQuery("select l from  Patients l",Patients.class).getResultList();
    }

    @Override
    public Patients getById(Long id) {
        return entityManager.find(Patients.class,id);
    }

    @Override
    public void updatePatients(Patients newPatient) {
       entityManager.merge(newPatient);

    }

    @Override
    public void deleteByPatientsId(Long id) {
        Patients patients = entityManager.find(Patients.class, id);
        entityManager.remove(patients);

    }
}
