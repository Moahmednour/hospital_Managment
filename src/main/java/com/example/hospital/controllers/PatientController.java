package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hospital.entities.Patient;
import com.example.hospital.service.PatientService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @GetMapping
    public String listPatients(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("page", patientService.getAllPatients(pageable));
        return "list-patients"; 
    }

    // @GetMapping
    // public String listPatients(Model model) {
    //     model.addAttribute("patients", patientService.getAllPatients());
    //     return "list-patients";
    // }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient-form"; 
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute @Valid Patient patient, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("patient", patient);
            return "patient-form";
        }
        patientService.savePatient(patient); 
        redirectAttributes.addFlashAttribute("operationSuccessful", true);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
