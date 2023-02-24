package peaksoft.repostitory;

import peaksoft.model.Appointment;

import java.util.List;

/**
 * The golden boy
 */
public interface AppointmentRepository {
    String save(Appointment appointment);
    Appointment getById(Long id);
    List<Appointment>getAll(Long id);
    void  update(Long id,Appointment newAppointment);
    void  delete(Long id );







}
