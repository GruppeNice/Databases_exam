package com.example.hospital_db_backend.model.entity_bases;

import com.example.hospital_db_backend.model.types.AppointmentStatusType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class AppointmentBase {
    protected LocalDate appointmentDate;
    protected String reason;
    @Enumerated(EnumType.STRING)
    protected AppointmentStatusType status;
}
