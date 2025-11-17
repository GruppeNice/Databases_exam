package com.example.hospital_db_backend.model.mongo_db;

import com.example.hospital_db_backend.model.entity_bases.PrescriptionBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "prescriptions")
public class Prescription extends PrescriptionBase {
    @Id
    private String prescriptionId;
    @DBRef
    private Patient patient;
    @DBRef
    private Doctor doctor;
    @DBRef
    private Medication medication;
}
