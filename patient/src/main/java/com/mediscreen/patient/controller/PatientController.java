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
    public Patient getPatientById(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }
    @GetMapping("/{firstName}/{lastName}")
    public Patient getPatientByFirstNameAndLastName(@PathVariable(value = "firstName") String firstName,@PathVariable(value = "lastName") String lastName) {
        return patientService.getPatientByFirstNameAndLastName(firstName, lastName);
    }
    @PostMapping("/add")
    public void addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
    }
    @PostMapping("/delete/{id}")
    public void deletePatient(@PathVariable(value = "id") Long id) {
        patientService.deletePatient(id);
    }
    @PostMapping("/update")
    public void updatePatient(@RequestBody Patient patient) {
        patientService.updatePatient(patient);
    }
}
