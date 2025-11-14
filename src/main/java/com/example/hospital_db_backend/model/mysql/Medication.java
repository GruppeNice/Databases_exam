package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.MedicationBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "medications")
public class Medication extends MedicationBase {
    @Id
    private UUID medicationId;
}
