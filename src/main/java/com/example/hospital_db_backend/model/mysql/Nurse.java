package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.NurseBase;
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
@Table(name = "nurses")
public class Nurse extends NurseBase {
    @Id
    private UUID nurseId;
    @ManyToMany
    private Set<Ward> wards;
}
