package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.DiagnosisBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "diagnosis", indexes = {
    @Index(name = "idx_diagnosis_date", columnList = "diagnosisDate"),
    @Index(name = "idx_diagnosis_patient_date", columnList = "patient_id,diagnosisDate"),
    @Index(name = "idx_diagnosis_doctor_date", columnList = "doctor_id,diagnosisDate")
})
public class Diagnosis extends DiagnosisBase {
    @Id
    private UUID diagnosisId;
    
    @ManyToMany(mappedBy = "diagnosis")
    @JsonIgnore
    private Set<Patient> patients;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
