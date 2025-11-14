package com.example.hospital_db_backend.model.entity_bases;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MedicationBase {
    protected String medicationName;
    protected String dosage;
}
