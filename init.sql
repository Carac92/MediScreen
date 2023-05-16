DROP DATABASE if exists patientdb;
CREATE DATABASE patientdb;
USE patientdb;
CREATE TABLE patient (
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
dob DATE NOT NULL,
gender CHAR NOT NULL,
address VARCHAR(255) NOT NULL,
phone_number VARCHAR(255) NOT NULL
);
