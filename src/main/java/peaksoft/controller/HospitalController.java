package hospital.controller;

import hospital.model.Hospital;
import hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * The golden boy
 */
@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    private final HospitalService service;
     @Autowired
    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("hospitals",service.getAll());
        return "hospital/hospitals";
    }
    @GetMapping("/addHospital")
    private String addHospital(Model model) {
        model.addAttribute( "newHospital", new Hospital());
        return "hospital/addHospital";
    }

    @PostMapping("/saveHospital")
    private String saveHospital(@ModelAttribute("hospital") Hospital hospital) throws IOException {
        service.save(hospital);
        return "redirect:/hospitals";
    }


    @GetMapping("/{id}/edit")
    private String updateHospital(@PathVariable("id") Long id, Model model) {
        model.addAttribute("oldHospital", service.getById(id));
        return "hospital/update";
    }

    @PatchMapping("/{id}/update")
    private String saveUpdate(@PathVariable("id")Long id,
            @ModelAttribute("hospital") Hospital hospital) {
        service.update(id,hospital);
        return "redirect:/hospitals";
    }

    @DeleteMapping("{hospitalId}/delete")
    private String deleteHospital(@PathVariable("hospitalId") Long hospitalId) {
        service.deleteById(hospitalId);
        return "redirect:/hospitals" ;
    }
}
