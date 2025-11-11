drop database if exists hospital_db;
create database hospital_db;
use hospital_db;

create table hospitals (
                           hospital_id int primary key auto_increment,
                           hospital_name varchar(100) not null,
                           address varchar(100) not null,
                           city varchar(50) not null
);

create table wards (
                       ward_id int primary key auto_increment,
                       ward_name enum (
                                 'Cardiology',
                                 'Neurology',
                                 'General Medicine'
                           ) not null,
                       hospital_id int not null,
                       max_capacity int not null,
                       foreign key (hospital_id) references hospitals(hospital_id)
);

create table patients (
                          patient_id int primary key auto_increment,
                          patient_name varchar(100) not null,
                          date_of_birth date not null,
                          ward_id int not null,
                          hospital_id int not null,
                          gender varchar(100),
                          foreign key (ward_id) references wards(ward_id),
                          foreign key (hospital_id) references hospitals(hospital_id)
);

create table doctors (
                         doctor_id int primary key auto_increment,
                         doctor_name varchar(100) not null,
                         specialty enum (
                                'Cardiology',
                                'Neurology',
                                'General Medicine',
                                'Surgery'
                             ) not null,
                         ward_id int not null ,
                         foreign key (ward_id) references wards(ward_id)
);

create table nurses (
                        nurse_id int primary key auto_increment,
                        nurse_name varchar(100) not null,
                        specialty enum (
                                'Emergency',
                                'ICU',
                                'General Care'
                             ) not null,
                        ward_id int not null ,
                        foreign key (ward_id) references wards(ward_id)
);

create table appointments (
                              appointment_id int primary key auto_increment,
                              patient_id int not null ,
                              doctor_id int,
                              nurse_id int,
                              appointment_date datetime not null,
                              reason varchar(255),
                              status enum ('Scheduled', 'Completed', 'Cancelled') not null,
                              foreign key (patient_id) references patients(patient_id),
                              foreign key (doctor_id) references doctors(doctor_id),
                              foreign key (nurse_id) references nurses(nurse_id)
);

create table medications (
                             medication_id int primary key auto_increment,
                             medication_name varchar(100) not null,
                             dosage varchar(50) not null
);

create table prescriptions (
                               prescription_id int primary key auto_increment,
                               patient_id int not null ,
                               doctor_id int not null ,
                               medication_id int not null ,
                               start_date date not null,
                               end_date date,
                               foreign key (patient_id) references patients(patient_id),
                               foreign key (doctor_id) references doctors(doctor_id),
                               foreign key (medication_id) references medications(medication_id)
);

create table diagnosis (
                           diagnosis_id int primary key auto_increment,
                           patient_id int not null ,
                           doctor_id int not null ,
                           diagnosis_date date not null,
                           description varchar(255) not null,
                           foreign key (patient_id) references patients(patient_id),
                           foreign key (doctor_id) references doctors(doctor_id)
);

create table surgeries (
                           surgery_id int primary key auto_increment,
                           patient_id int not null,
                           doctor_id int not null,
                           surgery_date datetime not null,
                           description varchar(255) not null,
                           foreign key (patient_id) references patients(patient_id),
                           foreign key (doctor_id) references doctors(doctor_id)
);