package com.example.hospital_db_backend.model.entity_bases;

import com.example.hospital_db_backend.model.types.WardType;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class WardBase {
    protected WardType type;
    protected int maxCapacity;
}
