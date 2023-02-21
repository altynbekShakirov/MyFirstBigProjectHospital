package hospital.service.serviceimpl;

import hospital.model.Department;
import hospital.repostitory.DepartmentRepository;
import hospital.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<Department> getAll() {
        return departmentRepository.getAll();
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
