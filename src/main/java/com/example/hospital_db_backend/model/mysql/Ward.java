package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.WardBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"wards"}) // Ignore the circular reference, but include hospitals
    private Set<Hospital> hospitals;
}
