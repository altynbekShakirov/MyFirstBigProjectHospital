package peaksoft.service.serviceimpl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.repostitory.*;
import peaksoft.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The golden boy
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final PatientsRepository patientsRepository;
    private final DoctorRepository doctorRepository;
   @Autowired
    public AppointmentServiceImpl( AppointmentRepository appointmentRepository, HospitalRepository hospitalRepository, DepartmentRepository departmentRepository, PatientsRepository patientsRepository, DoctorRepository doctorRepository) {
       this.appointmentRepository = appointmentRepository;
       this.hospitalRepository = hospitalRepository;
       this.departmentRepository = departmentRepository;
       this.patientsRepository = patientsRepository;
       this.doctorRepository = doctorRepository;
   }

   @Transactional
    @Override
    public void save(Long id,Appointment appointment) {
       Hospital hospital= hospitalRepository.getById(id);
        Appointment newAppointment=new Appointment();
        newAppointment.setId(appointment.getId());
        newAppointment.setDate(appointment.getDate());
        newAppointment.setDoctor(doctorRepository.getById(appointment.getDoctorId()));
        newAppointment.setDepartment(departmentRepository.getById(appointment.getDepartmentId()));
        newAppointment.setPatients(patientsRepository.getById(appointment.getPatientId()));
        hospital.addAppointment(newAppointment);
       System.out.println(appointmentRepository.save(newAppointment));
   }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.getById(id);
    }

    @Override
    public List<Appointment> getAll(Long id) {
        return appointmentRepository.getAll(id);
    }

    @Override
    public void update(Long id,Appointment newAppointment) {
       appointmentRepository.update(id,newAppointment);

    }

    @Override
    public void delete(Long id) {
       appointmentRepository.delete(id);

    }
}
