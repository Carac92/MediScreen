package com.mediscreen.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    public void testListPatients_shouldReturnListOfPatients() throws Exception {
        List<Patient> patients = Arrays.asList(
                new Patient(1L, "John", "Doe", LocalDate.of(1,1,1), "12345", 'M', "test"),
                new Patient(2L, "Jane", "Doe", LocalDate.of(1,1,1), "12345", 'F', "test")
        );
        given(patientService.getAllPatients()).willReturn(patients);

        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect((MockMvcResultMatchers.jsonPath("$[0].firstName").value("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Doe"));
    }

    @Test
    public void testGetPatientById_shouldReturnPatient() throws Exception {
        Patient patient = new Patient(1L, "John", "Doe", LocalDate.of(1,1,1), "12345", 'M', "test");
        given(patientService.getPatientById(1L)).willReturn(patient);

        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testGetPatientByFirstNameAndLastName_shouldReturnPatient() throws Exception {
        Patient patient = new Patient(1L, "John", "Doe", LocalDate.of(1,1,1), "12345", 'M', "test");
        when(patientService.getPatientByFirstNameAndLastName("John", "Doe")).thenReturn(patient);

        mockMvc.perform(get("/patient/John/Doe"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testAddPatient_shouldAddNewPatient() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        Patient patient = new Patient(1L,"John", "Doe", LocalDate.of(1,1,1), "12345", 'M', "test");

        mockMvc.perform(post("/patient/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk());

        verify(patientService, times(1)).addPatient(any());
    }

    @Test
    public void testDeletePatient_shouldDeletePatient() throws Exception {
        Long id = 1L;
        doNothing().when(patientService).deletePatient(id);

        mockMvc.perform((RequestBuilder) delete("/patient/delete/1"))
                .andExpect(status().isOk());

        verify(patientService, times(1)).deletePatient(id);
    }

    @Test
    public void testUpdatePatient_shouldUpdatePatient() throws Exception {
        Patient patient = new Patient(1L, "John", "Doe", LocalDate.of(1,1,1), "12345", 'M', "test");
        doNothing().when(patientService).updatePatient(patient);
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        mockMvc.perform(put("/patient/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk());

        verify(patientService, times(1)).updatePatient(any());
    }
}
