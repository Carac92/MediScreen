package com.mediscreen.patient.serviceTest;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.service.PatientService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;


    @Test
    public void testGetPatientById() {
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "12345", 'M', "test");
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Patient result = patientService.getPatientById(1L);

        Assertions.assertEquals(patient, result);
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPatientByFirstNameAndLastName() {
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "12345", 'M', "test");
        when(patientRepository.findPatientByFirstNameAndLastName("John", "Doe")).thenReturn(patient);

        Patient result = patientService.getPatientByFirstNameAndLastName("John", "Doe");

        Assertions.assertEquals(patient, result);
        verify(patientRepository, times(1)).findPatientByFirstNameAndLastName("John", "Doe");
    }

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1L, "John", "Doe", new Date(), "12345", 'M', "test"));
        patients.add(new Patient(2L, "Jane", "Doe", new Date(), "12345", 'F', "test"));
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getAllPatients();

        Assertions.assertEquals(patients, result);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testAddPatient() {
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "12345", 'M', "test");

        patientService.addPatient(patient);

        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testDeletePatient() {
        patientService.deletePatient(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdatePatient() {
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "12345", 'M', "test");

        patientService.updatePatient(patient);

        verify(patientRepository, times(1)).save(patient);
    }
}
