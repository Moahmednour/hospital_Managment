package com.example.hospital.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
@Getter @Setter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private LocalDateTime appointmentTime;

}
