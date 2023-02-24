package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Appointment;
import peaksoft.service.AppointmentService;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.PatientsService;

/**
 * The golden boy
 */
@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    private final PatientsService patientsService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, DepartmentService departmentService, PatientsService patientsService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.departmentService = departmentService;
        this.patientsService = patientsService;
    }
    @GetMapping("/{hospitalId}")
    String getAll(@PathVariable("hospitalId") Long id, Model model){
        model.addAttribute("appointments",appointmentService.getAll(id));
        return "appointment/appointments";
    }
    @GetMapping("/addAppointment/{hospitalId}")
    String addAppointment(@PathVariable("hospitalId") Long id ,Model model){
        Appointment appointment = new Appointment();
        model.addAttribute("newAppointment",appointment);
        model.addAttribute("doctors",doctorService.getAll(id));
        model.addAttribute("patients",patientsService.getAll(id));
        model.addAttribute("departments",departmentService.getAll(id));
        model.addAttribute("hospitalId",id);
        return "appointment/saveAppointment";
    }
    @PostMapping("/saveAppointment/{hospitalId}")
    String saveAppointment( @PathVariable("hospitalId")Long id,Appointment appointment){
        appointmentService.save(id,appointment);
        return "redirect:/appointments/"+id;
    }
    @GetMapping("/editAppointment/{hospitalId}/{appointmentId}")
    String editAppointment(Model model,@PathVariable("hospitalId")Long hospitalId ,@PathVariable("appointmentId")Long id ){
        model.addAttribute("appointment",appointmentService.getById(hospitalId));
        model.addAttribute("doctors",doctorService.getAll(hospitalId));
        model.addAttribute("patients",patientsService.getAll(hospitalId));
        model.addAttribute("departments",departmentService.getAll(hospitalId));
        model.addAttribute("appointmentId",id);
        model.addAttribute("hospitalId",hospitalId);
        return "appointment/updateAppointment";
    }
    @PutMapping("/update/{hospitalId}/{appointmentId}")
    String updateAppointment(@PathVariable("hospitalId")Long hospitalId,@PathVariable("appointmentId")Long appointmentId,Appointment appointment){
        appointmentService.update(appointmentId,appointment);
        return "redirect:/appointments/"+hospitalId;
    }
    @DeleteMapping("/deleteAppointment/{hospitalId}/{appointmentId}")
    String deleteAppointment(@PathVariable("hospitalId")Long hospitalId,@PathVariable("appointmentId")Long appointmentid){
        appointmentService.delete(appointmentid);
        return "redirect:/appointments/"+hospitalId;
    }

















}
