package com.mediscreen.patient.repository;

import com.mediscreen.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * This is the repository for the patient.
 * It extends JpaRepository to use the methods of JpaRepository.
 * It is used to communicate with MySQL database.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientByFirstNameAndLastName(String firstName, String lastName);
}
