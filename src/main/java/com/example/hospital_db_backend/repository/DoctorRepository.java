package com.example.hospital_db_backend.repository;

import com.example.hospital_db_backend.model.mysql.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    // Custom query to fetch doctor with ward and hospitals for detail view
    @Query("SELECT DISTINCT d FROM Doctor d LEFT JOIN FETCH d.ward w LEFT JOIN FETCH w.hospitals WHERE d.doctorId = :doctorId")
    Optional<Doctor> findByIdWithWardAndHospitals(@Param("doctorId") UUID doctorId);
}

