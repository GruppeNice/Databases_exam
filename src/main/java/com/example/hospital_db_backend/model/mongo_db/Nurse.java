package com.example.hospital_db_backend.model.mongo_db;

import com.example.hospital_db_backend.model.entity_bases.NurseBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "nurses")
public class Nurse extends NurseBase {
    @Id
    private UUID nurseId;
    @DBRef
    private Ward ward;
}
