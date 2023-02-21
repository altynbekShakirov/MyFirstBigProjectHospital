package hospital.service;

import hospital.model.Patients;

import java.util.List;

/**
 * The golden boy
 */
public interface PatientsService {
    String save(Long hospitalId, Patients patients);
    List<Patients> getAll();
    Patients getById(Long id);
    void  updatePatients(Patients newPatient);
    void deleteByPatientsId(Long id);
}
