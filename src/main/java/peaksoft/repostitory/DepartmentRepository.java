package hospital.repostitory;

import hospital.model.Department;

import java.util.List;

/**
 * The golden boy
 */
public interface DepartmentRepository {
    Department save(Long hospitalId,Department department);
    List<Department> getAll();
    void update(Long id ,Department newDepartment);
    void deleteById(Long id);

    Department getById(Long id);

}
