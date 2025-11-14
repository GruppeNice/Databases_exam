package com.example.hospital_db_backend.model.entity_bases;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class HospitalBase {
    protected String hospitalName;
    protected String address;
    protected String city;
}
