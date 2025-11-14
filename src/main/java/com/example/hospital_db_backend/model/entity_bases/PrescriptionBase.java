package com.example.hospital_db_backend.model.entity_bases;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public abstract class PrescriptionBase {
    protected LocalDate startDate;
    protected LocalDate endDate;
}
