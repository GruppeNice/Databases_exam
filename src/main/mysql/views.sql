create or replace view scheduled_appointments as
select a.appointment_id,
       p.patient_name,
       d.doctor_name,
       n.nurse_name,
       a.appointment_date,
       a.status
from appointments a
         join patients p on a.patient_id = p.patient_id
         join doctors d on a.doctor_id = d.doctor_id
         join nurses n on a.nurse_id = n.nurse_id
where a.appointment_date >= curdate()
  and a.status = 'Scheduled';

SELECT * FROM scheduled_appointments;