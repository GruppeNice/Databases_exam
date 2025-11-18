package com.example.hospital_db_backend.model.entity_bases;

import com.example.hospital_db_backend.model.types.WardType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class WardBase {
    @Enumerated(EnumType.STRING)
    protected WardType type;
    protected int maxCapacity;
}
