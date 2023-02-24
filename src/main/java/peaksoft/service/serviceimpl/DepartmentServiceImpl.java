package peaksoft.service.serviceimpl;

import peaksoft.model.Department;
import peaksoft.myExceptions.UniqueException;
import peaksoft.repostitory.DepartmentRepository;
import peaksoft.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * The golden boy
 */
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private  final DepartmentRepository departmentRepository;
    @Override
    public Department save(Long hospitalId, Department department) {
        return departmentRepository.save(hospitalId,department);
    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepository.getAll(id);
    }

    @Override
    public String assignDepartment(Long doctorId, Long departmentId) throws  UniqueException {
        return departmentRepository.assignDepartment(doctorId,departmentId);
    }

    @Override
    public void update(Long id, Department newDepartment) {
        departmentRepository.update(id,newDepartment);

    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);

    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.getById(id);
    }
}
