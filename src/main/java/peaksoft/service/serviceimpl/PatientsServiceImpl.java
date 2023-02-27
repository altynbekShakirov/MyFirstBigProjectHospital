package peaksoft.service.serviceimpl;

import jakarta.transaction.Transactional;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repostitory.AppointmentRepository;
import peaksoft.repostitory.PatientsRepository;
import peaksoft.service.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The golden boy
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PatientsServiceImpl implements PatientsService {

    private final PatientsRepository patientsRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public String save(Long hospitalId, Patient patients) {

        return patientsRepository.save(hospitalId, patients);
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
        patientsRepository.updatePatients(id, newPatient);

    }

    @Override
    public void deleteByPatientsId(Long id) {
        Patient patient = patientsRepository.getById(id);
        Hospital hospital = patient.getHospital();
        List<Appointment> appointments = patient.getAppointments();
        appointments.forEach(a -> a.getPatients().setAppointments(null));
        appointments.forEach(a -> a.getDoctor().setAppointments(null));
        hospital.getAppointments().removeAll(appointments);
        for (int i = 0; i < appointments.size(); i++) {
            appointmentRepository.delete(appointments.get(i).getId());
        }
        patientsRepository.deleteByPatientsId(id);
    }


}
