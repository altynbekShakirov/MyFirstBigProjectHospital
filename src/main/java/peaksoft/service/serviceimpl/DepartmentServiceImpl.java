package peaksoft.service.serviceimpl;

import jakarta.transaction.Transactional;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.repostitory.AppointmentRepository;
import peaksoft.repostitory.DepartmentRepository;
import peaksoft.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The golden boy
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Department save(Long hospitalId, Department department) {
        return departmentRepository.save(hospitalId, department);
    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepository.getAll(id);
    }

    @Override
    public String assignDepartment(Long doctorId, Long departmentId)  {
        return departmentRepository.assignDepartment(doctorId, departmentId);
    }

    @Override
    public void update(Long id, Department newDepartment) {
        departmentRepository.update(id, newDepartment);

    }

    @Override
    public void deleteById(Long id) {
        Department department = departmentRepository.getById(id);
        List<Appointment> appointments = department.getHospital().getAppointments();

        if (appointments != null) {
            List<Appointment> appointmentList = appointments.stream().filter(s -> s.getDepartment().getId().equals(id)).toList();
            appointmentList.forEach(s->appointmentRepository.delete(s.getId()));
        }

        List<Department> departments = department.getHospital().getDepartments();
        departments.removeIf(s->s.getId().equals(id));

        List<Doctor> doctors = department.getDoctors();
        doctors.forEach(d->d.getDepartments().removeIf(s->s.getId().equals(id)));


        departmentRepository.deleteById(id);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.getById(id);
    }
}
