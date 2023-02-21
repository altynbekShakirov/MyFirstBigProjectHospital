package hospital.repostitory;

import hospital.model.Appointment;

import java.util.List;

/**
 * The golden boy
 */
public interface AppointmentRepository {
    String save(Long hospitalId,Appointment appointment);
    List<Appointment>getAll();
    void  update(Appointment newAppointment);
    void  delete(Long id );





}
