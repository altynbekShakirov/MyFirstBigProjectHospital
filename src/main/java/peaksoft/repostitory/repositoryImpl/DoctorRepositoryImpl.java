package hospital.repostitory.repositoryImpl;

import hospital.model.Department;
import hospital.model.Doctor;
import hospital.model.Hospital;
import hospital.repostitory.DoctorRepository;
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
    public String assign(Long doctorId, Long departmentId) {
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        Department department = entityManager.find(Department.class, departmentId);

        return null;
    }

    @Override
    public List<Doctor> getAll() {
        return entityManager.createQuery("select c from  Doctor c",Doctor.class).getResultList();
    }

    @Override
    public Doctor getById(Long id) {
        return entityManager.find(Doctor.class,id);
    }

    @Override
    public void update( Doctor newDoctor) {
      entityManager.merge(newDoctor);
    }

    @Override
    public void delete(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        entityManager.remove(doctor);

    }
}
