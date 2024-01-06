package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.hospital.entities.Doctor;
import com.example.hospital.service.DoctorService;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "list-doctors";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        return "doctor-form";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
