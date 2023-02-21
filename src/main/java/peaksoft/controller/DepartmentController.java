package hospital.controller;

import hospital.model.Department;
import hospital.service.DepartmentService;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * The golden boy
 */
//@Controller
//@RequestMapping("/department")
public class DepartmentController {
//    private  final DepartmentService departmentService;
//
//    @Autowired
//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
//
//
//    @GetMapping
//    String  getAll(Model  model){
//        model.addAttribute("departments",departmentService.getAll());
//        return "department/departments";
//    }
//    @GetMapping("/addDepartment")
//    String addDepartment(Model model){
//        model.addAttribute("newDepartment",new Department());
//        return "department/save";
//    }
//    @PostMapping("/{hospitalId}/saveDepartment")
//    String saveDepartment(@ModelAttribute("department")Department department, @PathVariable("hospitalId")Long id){
//        departmentService.save(id,department);
//        return "redirect:/department/departments";
//    }

}
