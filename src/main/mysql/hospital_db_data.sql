-- Hospitals
insert into hospitals (hospital_name, address, city) values
                                                         ('Saint Mary Hospital', '123 Main St', 'Springfield'),
                                                         ('General Health Clinic', '45 Elm St', 'Rivertown');

-- Wards
insert into wards (ward_name, hospital_id, max_capacity) values
                                                         ('Cardiology', 1, 30),
                                                         ('Neurology', 1, 25),
                                                         ('General Medicine', 2, 20);

-- Patients
insert into patients (patient_name, date_of_birth, ward_id, hospital_id) values
                                                                             ('Alice Johnson', '1985-04-12', 1, 1),
                                                                             ('Bob Smith', '1990-07-23', 2, 1),
                                                                             ('Charlie Brown', '2010-02-15', 3, 2);

-- Doctors
insert into doctors (doctor_name, specialty, ward_id) values
                                                          ('Dr. Emily Carter', 'Cardiology', 1),
                                                          ('Dr. James Wilson', 'Neurology', 2),
                                                          ('Dr. Sarah Lee', 'Surgery', 3);

-- Nurses
insert into nurses (nurse_name, ward_id, specialty) values
                                             ('Nurse Anna White', 1, 'Emergency'),
                                             ('Nurse Tom Green', 2, 'ICU'),
                                             ('Nurse Lis
a Black', 3, 'General Care');

-- Appointments
insert into appointments (patient_id, doctor_id, nurse_id, appointment_date, reason, status) values
                                                                                         (1, 1, 1, '2025-10-10 09:00:00', 'Routine check-up', 'Scheduled'),
                                                                                         (2, 2, 2, '2025-10-11 14:30:00', 'Migraine consultation', 'Scheduled'),
                                                                                         (3, 3, 3, '2025-10-12 11:00:00', 'Child fever follow-up', 'Scheduled');


-- Medications
insert into medications (medication_name, dosage) values
                                                      ('Aspirin', '100mg'),
                                                      ('Amoxicillin', '500mg'),
                                                      ('Paracetamol', '250mg');

-- Prescriptions
insert into prescriptions (patient_id, doctor_id, medication_id, start_date, end_date) values
                                                                                           (1, 1, 1, '2025-09-01', '2025-09-15'),
                                                                                           (2, 2, 2, '2025-09-05', '2025-09-20'),
                                                                                           (3, 3, 3, '2025-09-08', null);

-- Diagnosis
insert into diagnosis (patient_id, doctor_id, diagnosis_date, description) values
                                                                               (1, 1, '2025-09-01', 'Hypertension'),
                                                                               (2, 2, '2025-09-05', 'Chronic migraine'),
                                                                               (3, 3, '2025-09-08', 'Viral infection');

-- Surgeries
insert into surgeries (patient_id, doctor_id, surgery_date, description) values
                                                                             (1, 1, '2025-09-02 10:00:00', 'Angioplasty'),
                                                                             (2, 2, '2025-09-06 13:00:00', 'Brain MRI (minor invasive procedure)');