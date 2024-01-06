package com.example.hospital.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
        List<Patient> findByAgeGreaterThanEqual(int age);
}
