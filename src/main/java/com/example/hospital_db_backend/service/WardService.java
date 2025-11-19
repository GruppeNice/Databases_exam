package com.example.hospital_db_backend.service;

import com.example.hospital_db_backend.dto.WardRequest;
import com.example.hospital_db_backend.model.mysql.Hospital;
import com.example.hospital_db_backend.model.mysql.Ward;
import com.example.hospital_db_backend.exception.EntityNotFoundException;
import com.example.hospital_db_backend.repository.HospitalRepository;
import com.example.hospital_db_backend.repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class WardService {
    private final WardRepository wardRepository;
    private final HospitalRepository hospitalRepository;

    public WardService(WardRepository wardRepository, HospitalRepository hospitalRepository) {
        this.wardRepository = wardRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public List<Ward> getWards() {
        return wardRepository.findAll();
    }

    public Ward getWardById(UUID id) {
        UUID wardId = Objects.requireNonNull(id, "Ward ID cannot be null");
        return wardRepository.findById(wardId)
                .orElseThrow(() -> new EntityNotFoundException("Ward not found"));
    }

    public Ward createWard(WardRequest request) {
        Ward ward = new Ward();
        ward.setWardId(UUID.randomUUID());
        ward.setType(request.getType());
        ward.setMaxCapacity(request.getMaxCapacity());

        if (request.getHospitalIds() != null && !request.getHospitalIds().isEmpty()) {
            Set<Hospital> hospitals = new HashSet<>();
            for (UUID hospitalId : request.getHospitalIds()) {
                UUID hospitalUuid = Objects.requireNonNull(hospitalId, "Hospital ID cannot be null");
                Hospital hospital = hospitalRepository.findById(hospitalUuid)
                        .orElseThrow(() -> new EntityNotFoundException("Hospital not found: " + hospitalId));
                hospitals.add(hospital);
            }
            ward.setHospitals(hospitals);
        }

        return wardRepository.save(ward);
    }

    public Ward updateWard(UUID id, WardRequest request) {
        UUID wardId = Objects.requireNonNull(id, "Ward ID cannot be null");
        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new EntityNotFoundException("Ward not found"));

        ward.setType(request.getType());
        ward.setMaxCapacity(request.getMaxCapacity());

        if (request.getHospitalIds() != null) {
            Set<Hospital> hospitals = new HashSet<>();
            for (UUID hospitalId : request.getHospitalIds()) {
                UUID hospitalUuid = Objects.requireNonNull(hospitalId, "Hospital ID cannot be null");
                Hospital hospital = hospitalRepository.findById(hospitalUuid)
                        .orElseThrow(() -> new EntityNotFoundException("Hospital not found: " + hospitalId));
                hospitals.add(hospital);
            }
            ward.setHospitals(hospitals);
        }

        return wardRepository.save(ward);
    }

    public void deleteWard(UUID id) {
        UUID wardId = Objects.requireNonNull(id, "Ward ID cannot be null");
        if (!wardRepository.existsById(wardId)) {
            throw new EntityNotFoundException("Ward not found");
        }
        wardRepository.deleteById(wardId);
    }
}

