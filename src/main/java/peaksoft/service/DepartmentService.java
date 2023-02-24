package peaksoft.service;

import peaksoft.model.Department;
import peaksoft.myExceptions.UniqueException;

import java.io.IOException;
import java.util.List;

/**
 * The golden boy
 */
public interface DepartmentService {
    Department save(Long hospitalId, Department department);
    List<Department> getAll(Long id );
    String assignDepartment(Long doctorId,Long departmentId) throws  UniqueException;
    void update(Long id ,Department newDepartment);
    void deleteById(Long id);

    Department getById(Long id);
}
