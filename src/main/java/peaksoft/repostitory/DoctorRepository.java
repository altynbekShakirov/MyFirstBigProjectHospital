package hospital.repostitory;

import hospital.model.Doctor;

import java.util.List;

/**
 * The golden boy
 */
public interface DoctorRepository {
    String  save(Long hospitalId,Doctor doctor);
     String assign(Long doctorId,Long departmentId);
     List<Doctor>getAll();
     Doctor getById(Long id);
     void  update(Doctor newDoctor);
     void delete(Long id);
}
