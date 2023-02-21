package hospital.controller;

import hospital.model.Patients;
import hospital.service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * The golden boy
 */
@Controller
@RequestMapping("patients")
public class PatientsController {
    private final PatientsService patientsService;
      @Autowired
    public PatientsController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }

    @GetMapping
    String getAll( Model model){
          model.addAttribute("patients",patientsService.getAll());
          return "patients/patients";
    }
    @GetMapping("/{hospitalId}/addPatient")
    String  addPatient(@PathVariable("hospitalId") Long id,Model model){
          model.addAttribute("addPatient",new Patients());
          model.addAttribute("courseId",id);
          return "patients/addPatients";
    }
    @PostMapping("/{hospitalId/savePatients")
    String savePatient(@PathVariable("hospitalId")Long hospitalId,@ModelAttribute("patientId") Patients patients){
          patientsService.save(hospitalId,patients);
          return "redirect:patients/patients";
    }






}
