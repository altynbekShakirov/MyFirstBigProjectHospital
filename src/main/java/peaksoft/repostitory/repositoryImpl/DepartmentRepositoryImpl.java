package peaksoft.repostitory.repositoryImpl;

import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repostitory.DepartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The golden boy
 */
@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Department save(Long hospitalId, Department department) {
        try {
            entityManager.persist(department);
            Hospital hospital = entityManager.find(Hospital.class, hospitalId);
            department.setHospital(hospital);
            return department;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    @Override
    public List<Department> getAll(Long id) {
        return entityManager.createQuery("select l from Department l join l.hospital h where  h.id=:id", Department.class).setParameter("id", id).getResultList();
    }

    @Override
    public String assignDepartment(Long doctorId, Long departmentId){
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        Department department = entityManager.find(Department.class, departmentId);
        doctor.addDepartment(department);
        department.addDoctor(doctor);

        entityManager.merge(department);
        entityManager.merge(doctor);

        return null;
    }

    @Override
    public void update(Long id, Department newDepartment) {
        Department department = entityManager.find(Department.class, id);
        department.setName(newDepartment.getName());


    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete  from Department  d where d.id=:id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public Department getById(Long id) {
        return entityManager.find(Department.class, id);
    }
}
