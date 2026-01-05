package com.example.hospital_db_backend.service;

import com.example.hospital_db_backend.dto.WardRequest;
import com.example.hospital_db_backend.model.mysql.Ward;
import com.example.hospital_db_backend.exception.EntityNotFoundException;
import com.example.hospital_db_backend.repository.WardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class WardService {
    private final WardRepository wardRepository;

    public WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public List<Ward> getWards() {
        return wardRepository.findAll();
    }

    public List<Ward> getWardsByHospitalId(UUID hospitalId) {
        UUID hospitalUuid = Objects.requireNonNull(hospitalId, "Hospital ID cannot be null");
        return wardRepository.findByHospitalId(hospitalUuid);
    }

    public Ward getWardById(UUID id) {
        UUID wardId = Objects.requireNonNull(id, "Ward ID cannot be null");
        return wardRepository.findByIdWithHospitals(wardId)
                .orElseThrow(() -> new EntityNotFoundException("Ward not found"));
    }

    @Transactional
    public Ward createWard(WardRequest request) {
        Ward ward = new Ward();
        ward.setWardId(UUID.randomUUID());
        ward.setType(request.getType());
        ward.setMaxCapacity(request.getMaxCapacity());

        return wardRepository.save(ward);
    }

    @Transactional
    public Ward updateWard(UUID id, WardRequest request) {
        UUID wardId = Objects.requireNonNull(id, "Ward ID cannot be null");
        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new EntityNotFoundException("Ward not found"));

        ward.setType(request.getType());
        ward.setMaxCapacity(request.getMaxCapacity());

        return wardRepository.save(ward);
    }

    @Transactional
    public void deleteWard(UUID id) {
        UUID wardId = Objects.requireNonNull(id, "Ward ID cannot be null");
        if (!wardRepository.existsById(wardId)) {
            throw new EntityNotFoundException("Ward not found");
        }
        wardRepository.deleteById(wardId);
    }
}

