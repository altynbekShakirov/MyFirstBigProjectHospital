package peaksoft.repostitory;

import peaksoft.model.Department;

import java.util.List;

/**
 * The golden boy
 */
public interface DepartmentRepository {
    Department save(Long hospitalId,Department department);
    List<Department> getAll(Long id );
    String assignDepartment(Long doctorId, Long departmentId);

    void update(Long id ,Department newDepartment);
    void deleteById(Long id);

    Department getById(Long id);

}
