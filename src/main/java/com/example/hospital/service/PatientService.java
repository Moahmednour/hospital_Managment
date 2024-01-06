package com.example.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dao.PatientRepository;
import com.example.hospital.entities.Patient;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientRepository.delete(patient);
    }
    
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
