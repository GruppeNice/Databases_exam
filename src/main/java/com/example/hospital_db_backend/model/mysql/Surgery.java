package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.SurgeryBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "surgeries")
public class Surgery extends SurgeryBase {
    @Id
    private UUID surgeryId;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
}
