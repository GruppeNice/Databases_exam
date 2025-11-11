drop trigger if exists adding_patient_to_full_ward;
delimiter //
create trigger adding_patient_to_full_ward
    before insert on patients
    for each row
begin
    declare ward_capacity int;
    declare current_patient_count int;
    declare ward_name varchar(50);

    select max_capacity into ward_capacity
    from wards
    where ward_id = new.ward_id;

    select count(*) into current_patient_count
    from patients
    where ward_id = new.ward_id;

    select ward_name into ward_name
    from wards
    where ward_id = new.ward_id;

    if current_patient_count >= ward_capacity then
        signal sqlstate '45000'
            set message_text = 'Cannot add patient to ward capacity full';
    end if;
end //