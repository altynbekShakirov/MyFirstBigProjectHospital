package hospital.service;

import hospital.model.Department;

import java.util.List;

/**
 * The golden boy
 */
public interface DepartmentService {
    Department save(Long hospitalId, Department department);
    List<Department> getAll();
    void update(Long id ,Department newDepartment);
    void deleteById(Long id);

    Department getById(Long id);
}
