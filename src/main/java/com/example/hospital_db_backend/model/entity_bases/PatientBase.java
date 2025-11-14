package com.example.hospital_db_backend.model.entity_bases;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public abstract class PatientBase {
    protected String patientName;
    protected LocalDate dateOfBirth;
    protected String gender;
}
