package com.example.hospital_db_backend.repository;

import com.example.hospital_db_backend.model.mysql.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WardRepository extends JpaRepository<Ward, UUID> {
    @Query("SELECT w FROM Ward w JOIN w.hospitals h WHERE h.hospitalId = :hospitalId")
    List<Ward> findByHospitalId(@Param("hospitalId") UUID hospitalId);
    
    @Query("SELECT DISTINCT w FROM Ward w LEFT JOIN FETCH w.hospitals WHERE w.wardId = :wardId")
    java.util.Optional<Ward> findByIdWithHospitals(@Param("wardId") UUID wardId);
}

