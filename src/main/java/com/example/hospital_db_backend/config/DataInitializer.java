package com.example.hospital_db_backend.config;

import com.example.hospital_db_backend.model.mysql.User;
import com.example.hospital_db_backend.model.types.Role;
import com.example.hospital_db_backend.repository.UserRepository;
import com.example.hospital_db_backend.repository.PatientRepository;
import com.example.hospital_db_backend.repository.DoctorRepository;
import com.example.hospital_db_backend.service.BulkDataSeederService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private static volatile boolean hasSeeded = false;
    private static final Object SEED_LOCK = new Object();

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BulkDataSeederService bulkDataSeederService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          BulkDataSeederService bulkDataSeederService,
                          PatientRepository patientRepository,
                          DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bulkDataSeederService = bulkDataSeederService;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) {
        logger.info("Starting data initialization...");
        
        // Initialize admin user
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUserId(UUID.randomUUID());
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            logger.info("Admin user created");
        }

        // Synchronized check to prevent multiple seeding attempts
        synchronized (SEED_LOCK) {
            if (hasSeeded) {
                logger.info("Seeder has already run in this JVM instance. Skipping.");
                return;
            }
            
            // Add a small delay to ensure database is fully initialized
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            long patientCount = patientRepository.count();
            long doctorCount = doctorRepository.count();
            
            logger.info("Current database state: {} patients, {} doctors", patientCount, doctorCount);
            
            if (patientCount == 0 && doctorCount == 0) {
                logger.info("Database is empty, seeding bulk data...");
                try {
                    Map<String, Integer> results = bulkDataSeederService.seedBulkData(
                        5,   // hospitals
                        50,  // patients
                        20,  // doctors
                        30   // nurses
                    );
                    
                    hasSeeded = true; // Mark as seeded
                    
                    logger.info("Bulk data seeding completed:");
                    results.forEach((key, value) -> 
                        logger.info("  {}: {}", key, value)
                    );
                } catch (Exception e) {
                    logger.error("Error seeding bulk data", e);
                }
            } else {
                hasSeeded = true; // Mark as seeded even if data exists
                logger.info("Database already contains data ({} patients, {} doctors). Skipping seed.", 
                    patientCount, doctorCount);
            }
        }
    }
}

