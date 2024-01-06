package com.example.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital.dao.AppointmentRepository;
import com.example.hospital.entities.Appointment;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setDoctor(appointmentDetails.getDoctor());
        appointment.setPatient(appointmentDetails.getPatient());
        appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found for id: " + id));
        appointmentRepository.delete(appointment);
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
