drop function if exists patient_age;
create function patient_age (p_id int) returns int
    deterministic
begin
    declare age int;
    select timestampdiff(year, date_of_birth, curdate()) into age
    from patients
    where patient_id = p_id;
    return age;
end;

select patient_name, patient_age(patient_id) as age from patients;