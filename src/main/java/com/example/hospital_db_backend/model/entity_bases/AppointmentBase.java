package com.example.hospital_db_backend.model.entity_bases;

import com.example.hospital_db_backend.model.types.AppointmentStatusType;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public abstract class AppointmentBase {
    protected LocalDate appointmentDate;
    protected String reason;
    protected AppointmentStatusType status;
}
