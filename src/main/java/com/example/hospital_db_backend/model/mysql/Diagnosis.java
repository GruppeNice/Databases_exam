package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.DiagnosisBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "diagnosis")
public class Diagnosis extends DiagnosisBase {
    @Id
    private UUID diagnosisId;
    @ManyToMany
    private Set<Patient> patients;
    @ManyToOne
    private Doctor doctor;
}
