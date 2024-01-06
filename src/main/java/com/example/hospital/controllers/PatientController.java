package com.example.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.entities.Patient;
import com.example.hospital.service.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "list-patients";
    }

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
    
    //  @GetMapping("/edit/{id}")
    // public String showEditForm(@PathVariable Long id, Model model) {
    //     model.addAttribute("patient", patientService.getPatientById(id));
    //     return "patient-form";
    // }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
