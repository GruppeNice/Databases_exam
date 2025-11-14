package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.PrescriptionBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "prescriptions")
public class Prescription extends PrescriptionBase {
    @Id
    private UUID prescriptionId;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Medication medication;
}
