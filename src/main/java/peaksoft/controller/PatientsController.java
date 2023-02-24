package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import peaksoft.model.Patient;
import peaksoft.model.enums.Gender;
import peaksoft.service.PatientsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * The golden boy
 */
@Controller
@RequestMapping("/patients")
public class PatientsController {
    private final PatientsService patientsService;
    private Long hospitalId;
    @Autowired

    public PatientsController(PatientsService patientsService) {
        this.patientsService = patientsService;
    }


    @GetMapping("/{hospitalId}")
    String getAllPatient(@PathVariable("hospitalId")Long id, Model model){
        model.addAttribute("hospitalId",id);
        model.addAttribute("patients",patientsService.getAll(id));
        hospitalId=id;
        return "patients/patients";
    }
    @GetMapping("/addPatients/{hospitalId}")
    String addPatient(@PathVariable("hospitalId") Long id ,Model model){
        Patient patients = new Patient();
        model.addAttribute("newPatient",patients);
        model.addAttribute("hospitalId",id);
        model.addAttribute("hospital",patientsService.getById(id));
        model.addAttribute("male", Gender.MALE.name());
        model.addAttribute("female",Gender.FEMALE.name());
        return "patients/addPatients";
    }
    @PostMapping("/savePatients/{hospitalId}")
    String savePatient(@PathVariable("hospitalId")Long id, Patient patients){
        patientsService.save(id,patients);
        return "redirect:/patients/"+id;
    }
    @DeleteMapping("/deletePatients/{patientId}")
    String delete(@PathVariable("patientId") Long id) {
        patientsService.deleteByPatientsId(id);
        return "redirect:/patients/"+hospitalId;
    }
    @GetMapping("/{patientId}/editPatients")
    String update(@PathVariable("patientId") Long id, Model model) {
        model.addAttribute("oldPatient",patientsService.getById(id));
        model.addAttribute("hospitalId",hospitalId);
        model.addAttribute("male", Gender.MALE.name());
        model.addAttribute("female",Gender.FEMALE.name());
        return "patients/updatePatient";
    }
    @PatchMapping("/updateSavePatient/{patientId}")
    String updateSavePatient(@PathVariable("patientId")Long id, Patient patients){
        patientsService.updatePatients(id, patients);
        return "redirect:/patients/"+hospitalId;
    }




}
