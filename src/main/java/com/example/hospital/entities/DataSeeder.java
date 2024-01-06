package com.example.hospital.entities;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.hospital.entities.Doctor;
import com.example.hospital.entities.Patient;
import com.example.hospital.service.DoctorService;
import com.example.hospital.service.PatientService;

@Component
public class DataSeeder implements CommandLineRunner {

    private final DoctorService doctorService;
    private final PatientService patientService;

    public DataSeeder(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed Doctors
        String[] doctorNames = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis"};
        String[] specializations = { "Cardiology", "Neurology", "Pediatrics", "Orthopedics", "General", "Dermatology", "Ophthalmology"};

        for (int i = 0; i < doctorNames.length; i++) {
            Doctor doctor = new Doctor(null, "Dr. " + doctorNames[i], specializations[i % specializations.length]);
            doctorService.saveDoctor(doctor);
        }

        // Seed Patients
        String[] patientNames = { "Alice", "Bob", "Charlie", "Diana", "Edward", "Fiona", "George" };
        int[] ages = { 30, 45, 28, 34, 40, 22, 37};

        for (int i = 0; i < patientNames.length; i++) {
            Patient patient = new Patient(null, patientNames[i] + " Johnson", ages[i]);
            patientService.savePatient(patient);
        }
    }
}
