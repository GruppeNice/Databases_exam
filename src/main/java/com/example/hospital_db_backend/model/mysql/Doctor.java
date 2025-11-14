package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.DoctorBase;
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
@Table(name = "doctors")
public class Doctor extends DoctorBase {
    @Id
    private UUID doctorId;
    @ManyToMany
    private Set<Ward> wards;
}
