package hospital.service.serviceimpl;

import hospital.model.Hospital;
import hospital.model.Patients;
import hospital.repostitory.PatientsRepository;
import hospital.service.PatientsService;
import jakarta.persistence.EntityManager;
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
    public String save(Long hospitalId, Patients patients) {

        return patientsRepository.save(hospitalId,patients);
    }

    @Override
    public List<Patients> getAll() {
        return patientsRepository.getAll();
    }

    @Override
    public Patients getById(Long id) {
        return patientsRepository.getById(id);
    }

    @Override
    public void updatePatients(Patients newPatient) {
        patientsRepository.updatePatients(newPatient);

    }

    @Override
    public void deleteByPatientsId(Long id) {
     patientsRepository.deleteByPatientsId(id);
    }
}
