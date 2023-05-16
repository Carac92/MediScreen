package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * This is the service for the patient.
 * It is used to communicate with the controller.
 * It communicates with the repository.
 */
@Service
@Slf4j
public class PatientService {
    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
    public Patient getPatientByFirstNameAndLastName(String firstName, String lastName) {
        log.info("PatientService.getPatientByFirstNameAndLastName: firstName={}, lastName={}",
                firstName, lastName);
        return patientRepository.findPatientByFirstNameAndLastName(firstName, lastName);
    }
    public List<Patient> getAllPatients() {
        log.info("PatientService.getAllPatients");
        return patientRepository.findAll();
    }
    public void addPatient(Patient patient) {
        log.info("PatientService.addPatient: patient={}", patient.toString());
        patientRepository.save(patient);
    }
    public void deletePatient(Long id) {
        log.info("PatientService.deletePatient: id={}", id);
        patientRepository.deleteById(id);
    }
    public void updatePatient(Patient patient) {
        log.info("PatientService.updatePatient: patient={}", patient.toString());
        patientRepository.save(patient);
    }
}
