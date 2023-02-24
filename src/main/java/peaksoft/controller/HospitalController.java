package peaksoft.controller;

import peaksoft.model.Hospital;
import peaksoft.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



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
     String getAll(Model model){
        model.addAttribute("hospitals",service.getAll());
        return "hospital/hospitals";
    }
    @GetMapping("/addHospital")
      String addHospital(Model model) {
        model.addAttribute( "newHospital", new Hospital());
        return "hospital/addHospital";
    }

    @PostMapping("/saveHospital")
    String saveHospital(@ModelAttribute("hospital") Hospital hospital)  {
        service.save(hospital);
        return "redirect:/hospitals";
    }


    @GetMapping("/{id}/edit")
     String updateHospital(@PathVariable("id") Long id, Model model) {
        model.addAttribute("oldHospital", service.getById(id));
        return "hospital/update";
    }

    @PatchMapping("/{id}/updateHospital")
     String saveUpdate(@PathVariable("id")Long id,
            @ModelAttribute("hospital") Hospital hospital) {
        service.update(id,hospital);
        return "redirect:/hospitals";
    }

    @DeleteMapping("{hospitalId}/deleteHospital")
     String deleteHospital(@PathVariable("hospitalId") Long hospitalId) {
        service.deleteById(hospitalId);
        return "redirect:/hospitals" ;
    }

    @GetMapping("/profile/{hospitalId}")
         String  profile(Model model,@PathVariable("hospitalId")Long id ){
         model.addAttribute("hospital",service.getById(id));
         model.addAttribute("hospitalId",id);
         return "hospital/profile";

    }


}
