package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.WardBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "wards")
public class Ward extends WardBase {
    @Id
    private UUID wardId;
    @ManyToMany
    private Set<Hospital> hospitals;
}
