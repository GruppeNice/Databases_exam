package com.example.hospital_db_backend.model.mysql;

import com.example.hospital_db_backend.model.entity_bases.AppointmentBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment extends AppointmentBase {
    @Id
    private UUID appointmentId;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Nurse nurse;
}
