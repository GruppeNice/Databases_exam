package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.HospitalBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "hospitals")
public class Hospital extends HospitalBase {
    @Id
    private UUID hospitalId;
    @ManyToMany
    private Set<Ward> wards;
}
