package com.example.hospital_db_backend.service;

import com.example.hospital_db_backend.dto.HospitalRequest;
import com.example.hospital_db_backend.model.mysql.Hospital;
import com.example.hospital_db_backend.exception.EntityNotFoundException;
import com.example.hospital_db_backend.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(UUID id) {
        UUID hospitalId = Objects.requireNonNull(id, "Hospital ID cannot be null");
        return hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));
    }

    @Transactional
    public Hospital createHospital(HospitalRequest request) {
        Hospital hospital = new Hospital();
        hospital.setHospitalId(UUID.randomUUID());
        hospital.setHospitalName(request.getHospitalName());
        hospital.setAddress(request.getAddress());
        hospital.setCity(request.getCity());

        return hospitalRepository.save(hospital);
    }

    @Transactional
    public Hospital updateHospital(UUID id, HospitalRequest request) {
        UUID hospitalId = Objects.requireNonNull(id, "Hospital ID cannot be null");
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("Hospital not found"));

        hospital.setHospitalName(request.getHospitalName());
        hospital.setAddress(request.getAddress());
        hospital.setCity(request.getCity());

        return hospitalRepository.save(hospital);
    }

    @Transactional
    public void deleteHospital(UUID id) {
        UUID hospitalId = Objects.requireNonNull(id, "Hospital ID cannot be null");
        if (!hospitalRepository.existsById(hospitalId)) {
            throw new EntityNotFoundException("Hospital not found");
        }
        hospitalRepository.deleteById(hospitalId);
    }
}

