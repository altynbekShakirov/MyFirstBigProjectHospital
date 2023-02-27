package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;

/**
 * The golden boy
 */
@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private  final DepartmentService departmentService;
    private Long hospitalId;


    @Autowired
    public DoctorController(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }
    @GetMapping("/{hospitalId}")
    String getAllDoctors(@PathVariable("hospitalId")Long id, Model model,
                         @ModelAttribute("department")Department department){
        model.addAttribute("departments",departmentService.getAll(id));
        model.addAttribute("hospitalId",id);
        model.addAttribute("doctors",doctorService.getAll(id));
        model.addAttribute("departments",departmentService.getAll(id));
        hospitalId=id;
        return "doctor/doctors";
    }
    @GetMapping("/addDoctor/{hospitalId}")
    String addDepartment(@PathVariable("hospitalId") Long id ,Model model){

        model.addAttribute("newDoctor",new Doctor());
        model.addAttribute("hospitalId",id);
        model.addAttribute("hospital",doctorService.getById(id));
        return "doctor/doctorSave";
    }
    @PostMapping("/saveDoctor/{hospitalId}")
    String saveDepartment( @PathVariable("hospitalId")Long id,Doctor doctor){
        doctorService.save(id,doctor);
        return "redirect:/doctors/"+id;
    }
    @DeleteMapping("/deleteDoctor/{doctorId}")
    String deleteHospital(@PathVariable("doctorId") Long id) {
        doctorService.delete(id);
        return "redirect:/doctors/"+hospitalId;
    }
    @GetMapping("/{doctorId}/editDoctor")
    String updateHospital(@PathVariable("doctorId") Long id, Model model) {
        model.addAttribute("oldDoctor",doctorService.getById(id));
        model.addAttribute("hospitalId",hospitalId);
        return "doctor/updateDoctor";
    }
    @PostMapping("/updateSaveDoctor/{doctorId}")
    String updateSaveDepartment( @PathVariable("doctorId")Long id,Doctor doctor){
        doctorService.update(id, doctor);
        return "redirect:/doctors/"+hospitalId;
    }


     @PostMapping("/{doctorId}/assignDoctor")
    private String assignDoctor(@PathVariable("doctorId") Long doctorId,
                                @ModelAttribute("department") Department department){
        Long id = department.getId();
        departmentService.assignDepartment(doctorId,id);
        return "redirect:/doctors/" + hospitalId;
    }





}
