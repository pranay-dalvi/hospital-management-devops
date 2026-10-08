package com.hospital.management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/register")
    public String registerPatient(
            @RequestParam String name,
            @RequestParam String age,
            @RequestParam String disease,
            Model model) {

        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("disease", disease);
        model.addAttribute("registered", true);

        return "index";
    }
}