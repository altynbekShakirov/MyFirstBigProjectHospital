package peaksoft.service.serviceimpl;

import peaksoft.model.Doctor;
import peaksoft.repostitory.DoctorRepository;
import peaksoft.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The golden boy
 */
@Service

public class DoctorServiceImpl implements DoctorService {
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    private final DoctorRepository doctorRepository;
    @Override
    public String save(Long hospitalId, Doctor doctor) {
        return doctorRepository.save(hospitalId, doctor);
    }


    @Override
    public List<Doctor> getAll(Long id) {
        return doctorRepository.getAll(id);
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepository.getById(id);
    }

    @Override
    public void update(Long id,Doctor newDoctor) {
        doctorRepository.update(id,newDoctor);

    }

    @Override
    public void delete(Long id) {
        doctorRepository.delete(id);

    }
}
