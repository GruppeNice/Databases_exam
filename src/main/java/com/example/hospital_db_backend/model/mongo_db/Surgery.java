package com.example.hospital_db_backend.model.mongo_db;

import com.example.hospital_db_backend.model.entity_bases.SurgeryBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "surgeries")
public class Surgery extends SurgeryBase {
    @Id
    private UUID surgeryId;
    @DBRef
    private Patient patient;
    @DBRef
    private Doctor doctor;
}
