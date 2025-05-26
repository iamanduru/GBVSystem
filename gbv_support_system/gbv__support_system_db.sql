CREATE DATABASE IF NOT EXISTS gbv_support_system_db;
USE gbv_support_system_db;

-- Table: report_categories
CREATE TABLE report_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO report_categories (category_name)
VALUES 
('Physical Abuse'), 
('Emotional Abuse'), 
('Sexual Harassment'), 
('Verbal Abuse');

-- Table: reports
CREATE TABLE reports (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    survivor_alias VARCHAR(100),
    category_id INT,
    location VARCHAR(100),
    description TEXT,
    date_reported DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES report_categories(category_id)
);

-- Table: support_services
CREATE TABLE support_services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(100),
    contact_info VARCHAR(100),
    service_type VARCHAR(50),
    availability_hours VARCHAR(100),
    location VARCHAR(100)
);

-- Table: users
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    full_name VARCHAR(100),
    role VARCHAR(50),
    contact_email VARCHAR(100)
);

-- Table: feedback
CREATE TABLE feedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    survivor_alias VARCHAR(100),
    message TEXT,
    submitted_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
