package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
    public Patient getPatientByFirstNameAndLastName(String firstName, String lastName) {
        return patientRepository.findPatientByFirstNameAndLastName(firstName, lastName);
    }
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }
}
