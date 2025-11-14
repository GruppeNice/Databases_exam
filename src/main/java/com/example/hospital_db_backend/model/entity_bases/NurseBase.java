package com.example.hospital_db_backend.model.entity_bases;

import com.example.hospital_db_backend.model.types.NurseSpecialityType;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NurseBase {
    protected String nurseName;
    protected NurseSpecialityType speciality;
}
