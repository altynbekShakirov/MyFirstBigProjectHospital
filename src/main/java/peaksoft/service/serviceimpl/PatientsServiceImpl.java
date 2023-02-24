package peaksoft.service.serviceimpl;

import peaksoft.model.Patient;
import peaksoft.repostitory.PatientsRepository;
import peaksoft.service.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The golden boy
 */
@Service
@RequiredArgsConstructor
public class PatientsServiceImpl implements PatientsService {

    private final PatientsRepository patientsRepository;
    @Override
    public String save(Long hospitalId, Patient patients) {

        return patientsRepository.save(hospitalId,patients);
    }

    @Override
    public List<Patient> getAll(Long id) {
        return patientsRepository.getAll(id);
    }

    @Override
    public Patient getById(Long id) {
        return patientsRepository.getById(id);
    }

    @Override
    public void updatePatients(Long id, Patient newPatient) {
        patientsRepository.updatePatients(id,newPatient);

    }

    @Override
    public void deleteByPatientsId(Long id) {
     patientsRepository.deleteByPatientsId(id);
    }
}
