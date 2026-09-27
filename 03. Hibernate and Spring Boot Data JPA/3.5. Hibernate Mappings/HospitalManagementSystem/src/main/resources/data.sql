INSERT INTO insurance (policy_number, provider, valid_until, created_at)
VALUES
('POL1001', 'LIC', '2028-12-31', NOW()),
('POL1002', 'Star Health', '2027-09-15', NOW()),
('POL1003', 'ICICI Lombard', '2029-05-20', NOW()),
('POL1004', 'Niva Bupa', '2028-03-10', NOW()),
('POL1005', 'HDFC ERGO', '2027-11-25', NOW());

INSERT INTO doctor (name, specialization, email, created_at)
VALUES
('Dr. Rajesh Sharma', 'Cardiology', 'rajesh.sharma@gmail.com', NOW()),
('Dr. Priya Nair', 'Neurology', 'priya.nair@gmail.com', NOW()),
('Dr. Arjun Reddy', 'Orthopedics', 'arjun.reddy@gmail.com', NOW()),
('Dr. Sneha Patil', 'Dermatology', 'sneha.patil@gmail.com', NOW()),
('Dr. Vikram Singh', 'Pediatrics', 'vikram.singh@gmail.com', NOW());

INSERT INTO patient
(name, gender, birth_date, email, blood_group, created_at, insurance_id)
VALUES
('Amit Kumar', 'Male', '1995-04-12', 'amit.kumar@gmail.com', 'A_POSITIVE', NOW(), 1),

('Neha Joshi', 'Female', '1998-09-25', 'neha.joshi@gmail.com', 'B_POSITIVE', NOW(), 2),

('Rohan Verma', 'Male', '1992-01-15', 'rohan.verma@gmail.com', 'O_POSITIVE', NOW(), 3),

('Pooja Mehta', 'Female', '2000-06-18', 'pooja.mehta@gmail.com', 'AB_POSITIVE', NOW(), 4),

('Karan Patel', 'Male', '1997-11-30', 'karan.patel@gmail.com', 'O_NEGATIVE', NOW(), 5);

INSERT INTO department
(name, created_at, head_doctor_id)
VALUES
('Cardiology', NOW(), 1),
('Neurology', NOW(), 2),
('Orthopedics', NOW(), 3),
('Dermatology', NOW(), 4),
('Pediatrics', NOW(), 5);

INSERT INTO appointment
(appointment_time, reason, status, patient_id, doctor_id)
VALUES

('2026-07-10 09:00:00', 'Chest Pain', 'Scheduled', 1, 1),

('2026-07-11 10:30:00', 'Migraine', 'Completed', 2, 2),

('2026-07-12 14:00:00', 'Knee Injury', 'Scheduled', 3, 3),

('2026-07-13 16:00:00', 'Skin Allergy', 'Cancelled', 4, 4),

('2026-07-14 11:15:00', 'Child Fever', 'Completed', 5, 5);