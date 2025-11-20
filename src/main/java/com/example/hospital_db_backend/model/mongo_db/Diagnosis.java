package com.example.hospital_db_backend.model.mongo_db;

import com.example.hospital_db_backend.model.entity_bases.DiagnosisBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "diagnosis")
public class Diagnosis extends DiagnosisBase {
    @Id
    private UUID diagnosisId;
    @DBRef
    private Doctor doctor;
}
