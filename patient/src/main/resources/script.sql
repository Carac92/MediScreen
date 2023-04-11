DROP DATABASE if exists patientdb;
CREATE DATABASE patientdb;
USE patientdb;
CREATE TABLE patient (
id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
birthdate DATE NOT NULL,
gender BIT NOT NULL,
phone_number INTEGER NOT NULL
);
CREATE TABLE address (
id         INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
patient_id INTEGER      NOT NULL,
number     INTEGER      NOT NULL,
street     VARCHAR(255) NOT NULL,
city       VARCHAR(255) NOT NULL,
zip_code   INTEGER      NOT NULL,
FOREIGN KEY (patient_id) REFERENCES patient (id)
);