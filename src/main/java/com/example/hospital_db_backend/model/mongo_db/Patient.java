package com.example.hospital_db_backend.model.mongo_db;

import com.example.hospital_db_backend.model.entity_bases.PatientBase;
import com.example.hospital_db_backend.model.mysql.Ward;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "patients")
public class Patient extends PatientBase {
    @Id
    private UUID patientId;
    @DBRef
    private Ward ward;
    @DBRef
    private Hospital hospital;

}
