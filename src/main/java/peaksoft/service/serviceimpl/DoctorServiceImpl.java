package peaksoft.service.serviceimpl;

import jakarta.transaction.Transactional;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repostitory.AppointmentRepository;
import peaksoft.repostitory.DoctorRepository;
import peaksoft.repostitory.PatientsRepository;
import peaksoft.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The golden boy
 */
@Service
@RequiredArgsConstructor
@Transactional

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

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
    public void update(Long id, Doctor newDoctor) {
        doctorRepository.update(id, newDoctor);

    }

    @Override
    public void delete(Long id) {
        Doctor doctor = doctorRepository.getById(id);
        Hospital hospital = doctor.getHospital();
        List<Appointment> appointments = doctor.getAppointments();
        appointments.forEach(a -> a.getDoctor().setAppointments(null));
        appointments.forEach(a -> a.getPatients().setAppointments(null));
        hospital.getAppointments().removeAll(appointments);

        for (int i = 0; i < appointments.size(); i++) {
                appointmentRepository.delete(appointments.get(i).getId());
        }
        doctorRepository.delete(id);
    }

    @Override
    public int countDoctors(Long id) {
        return doctorRepository.getAll(id).size();
    }
}
