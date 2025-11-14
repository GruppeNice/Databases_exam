package com.example.hospital_db_backend.model.entity_bases;

import com.example.hospital_db_backend.model.types.DoctorSpecialityType;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DoctorBase {
    protected String doctorName;
    protected DoctorSpecialityType speciality;
}
