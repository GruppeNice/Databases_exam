drop procedure if exists patients_in_ward;
create procedure patients_in_ward (in w_id int)
begin
    select p.patient_id, p.patient_name, p.date_of_birth, w.ward_name, h.hospital_name
    from patients p
             join wards w on p.ward_id = w.ward_id
             join hospitals h on p.hospital_id = h.hospital_id
    where w.ward_id = w_id;
end;

call patients_in_ward(1);