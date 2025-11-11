drop event if exists remove_old_cancelled_appointments;
create event remove_old_cancelled_appointments
    on schedule every 1 day
    starts '2025-10-01 00:00:00'
    do
        delete from appointments
        where status = 'Cancelled' and appointment_date < NOW() - INTERVAL 30 DAY;