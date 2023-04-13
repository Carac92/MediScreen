package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Get all patients")
    @GetMapping("/list")
    public List<Patient> listPatients() {
        return patientService.getAllPatients();
    }
    @Operation(summary = "Get patient by id")
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }
    @Operation(summary = "Get patient by first name and last name")
    @GetMapping("/{firstName}/{lastName}")
    public Patient getPatientByFirstNameAndLastName(@PathVariable(value = "firstName") String firstName,@PathVariable(value = "lastName") String lastName) {
        return patientService.getPatientByFirstNameAndLastName(firstName, lastName);
    }
    @Operation(summary = "Add a patient")
    @PostMapping("/add")
    public void addPatient(@RequestBody @Valid Patient patient) {
        patientService.addPatient(patient);
    }
    @Operation(summary = "Delete a patient")
    @PostMapping("/delete/{id}")
    public void deletePatient(@PathVariable(value = "id") Long id) {
        patientService.deletePatient(id);
    }
    @Operation(summary = "Update a patient")
    @PostMapping("/update")
    public void updatePatient(@RequestBody @Valid Patient patient) {
        patientService.updatePatient(patient);
    }
}
