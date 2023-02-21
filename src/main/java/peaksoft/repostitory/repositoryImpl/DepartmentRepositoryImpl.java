package hospital.repostitory.repositoryImpl;

import hospital.model.Department;
import hospital.model.Hospital;
import hospital.repostitory.DepartmentRepository;
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
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private  final EntityManager entityManager;

     @Autowired
    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Department save(Long hospitalId, Department department) {
        entityManager.persist(department);
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        department.setHospital(hospital);
        return department;
    }

    @Override
    public List<Department> getAll() {
        return entityManager.createQuery("select l from Department l",Department.class).getResultList();
    }

    @Override
    public void update(Long id, Department newDepartment) {
        Department department = entityManager.find(Department.class, id);
        department.setName(newDepartment.getName());


    }

    @Override
    public void deleteById(Long id) {
        Department department = entityManager.find(Department.class, id);
        entityManager.remove(department);

    }

    @Override
    public Department getById(Long id) {
        return entityManager.find(Department.class, id);

    }
}
