package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

/**
 * The golden boy
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private  final DepartmentService departmentService;
    private  final HospitalService hospitalService;
    private Long hospitalId;


    @Autowired
    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;

        this.hospitalService = hospitalService;
    }


    @GetMapping("/{hospitalId}")
    String  getAll(Model model,@PathVariable("hospitalId")Long id ){
        model.addAttribute("id",id);
            model.addAttribute("departments", departmentService.getAll(id));
        model.addAttribute("hospital",hospitalService.getById(id));
        hospitalId=id;
        return "department/departments";
    }
    @GetMapping("/addDepartment/{hospitalId}")
    String addDepartment(@PathVariable("hospitalId") Long id ,Model model){
        model.addAttribute("newDepartment",new Department());
        model.addAttribute("hospitalId",id);
        model.addAttribute("hospital",hospitalService.getById(id));
        return "department/save";
    }
    @PostMapping("/saveDepartment/{hospitalId}")
    String saveDepartment( @PathVariable("hospitalId")Long id,Department department){
        departmentService.save(id,department);
        return "redirect:/departments/"+id;
    }
    @DeleteMapping("/deleteDepartment/{departmentId}")
    String deleteHospital(@PathVariable("departmentId") Long id) {
        departmentService.deleteById(id);
        return "redirect:/departments/"+hospitalId;
    }
    @GetMapping("/{departmentId}/editDepartment")
    String updateHospital(@PathVariable("departmentId") Long id, Model model) {
        model.addAttribute("oldDepartment",departmentService.getById(id));
        model.addAttribute("hospitalId",hospitalId);
        return "department/updateDepartment";
    }
    @PostMapping("/updateSaveDepartment/{departmentId}")
    String updateSaveDepartment( @PathVariable("departmentId")Long id,Department department){
        departmentService.update(id, department);
        return "redirect:/departments/"+hospitalId;
    }







}
