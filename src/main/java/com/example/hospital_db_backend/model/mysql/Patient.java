package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.PatientBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "patients", indexes = {
    @Index(name = "idx_patient_name", columnList = "patientName")
})
public class Patient extends PatientBase {
    @Id
    private UUID patientId;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Ward ward;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Hospital hospital;
    
    @ManyToMany(mappedBy = "patients")
    @JsonIgnore
    private Set<Diagnosis> diagnosis;

}
