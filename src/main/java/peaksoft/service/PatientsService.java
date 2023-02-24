package peaksoft.service;

import peaksoft.model.Patient;

import java.util.List;

/**
 * The golden boy
 */
public interface PatientsService {
    String save(Long hospitalId, Patient patients);
    List<Patient> getAll(Long id);
    Patient getById(Long id);
    void  updatePatients(Long id, Patient newPatient);
    void deleteByPatientsId(Long id);
}
