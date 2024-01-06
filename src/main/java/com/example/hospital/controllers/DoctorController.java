package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.example.hospital.entities.Doctor;
import com.example.hospital.service.DoctorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listDoctors(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("page", doctorService.getAllDoctors(pageable));
        return "list-doctors";
    }

    // @GetMapping
    // public String listDoctors(Model model) {
    //     model.addAttribute("doctors", doctorService.getAllDoctors());
    //     return "list-doctors";
    // }

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
public String saveDoctor(@ModelAttribute @Valid Doctor doctor, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
        model.addAttribute("doctor", doctor);
        return "doctor-form";
    }
    doctorService.saveDoctor(doctor);
    redirectAttributes.addFlashAttribute("operationSuccessful", true);
    return "redirect:/doctors";
}

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}