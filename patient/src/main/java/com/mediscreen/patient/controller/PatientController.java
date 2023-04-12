package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    
    @GetMapping("/list")
    public List<Patient> listPatients() {
        return patientService.getAllPatients();
    }
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
    @GetMapping("/firstName={firstName}&lastName={lastName}")
    public Patient getPatientByFirstNameAndLastName(@PathVariable String firstName,
                                                    @PathVariable String lastName) {
        return patientService.getPatientByFirstNameAndLastName(firstName, lastName);
    }
    @PutMapping("/add")
    public void addPatient(@RequestParam Patient patient) {
        patientService.addPatient(patient);
    }
    @PutMapping("/delete/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
    @PutMapping("/update")
    public void updatePatient(@RequestParam Patient patient) {
        patientService.updatePatient(patient);
    }
}
