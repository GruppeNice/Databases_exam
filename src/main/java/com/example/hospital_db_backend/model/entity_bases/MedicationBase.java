package com.example.hospital_db_backend.model.entity_bases;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class MedicationBase {
    protected String medicationName;
    protected String dosage;
}
