CREATE DATABASE IF NOT EXISTS employment_db;

USE employment_db;

CREATE TABLE applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    current_address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(20) NOT NULL,
    email_address VARCHAR(100) NOT NULL,
    education VARCHAR(100) NOT NULL,
    gender VARCHAR(30) NOT NULL,
    date_available DATE NOT NULL,
    desired_position VARCHAR(100) NOT NULL,
    desired_salary DECIMAL(10,2) NOT NULL,
    legally_authorized VARCHAR(10) NOT NULL,
    relatives_working VARCHAR(10) NOT NULL,
    explanation TEXT,
    applicant_signature VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM applications;