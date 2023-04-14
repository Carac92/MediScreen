package com.mediscreen.patient.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.controller.PatientController;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    public void listPatients_shouldReturnListOfPatients() throws Exception {
        List<Patient> patients = Arrays.asList(
                new Patient(1L, "John", "Doe", new Date(), "12345", 'M', "test"),
                new Patient(2L, "Jane", "Doe", new Date(), "12345", 'F', "test")
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
    public void getPatientById_shouldReturnPatient() throws Exception {
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "12345", 'M', "test");
        given(patientService.getPatientById(1L)).willReturn(patient);

        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void getPatientByFirstNameAndLastName_shouldReturnPatient() throws Exception {
        Patient patient = new Patient(1L, "John", "Doe", new Date(), "12345", 'M', "test");
        when(patientService.getPatientByFirstNameAndLastName("John", "Doe")).thenReturn(patient);

        mockMvc.perform(get("/patient/John/Doe"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void addPatient_shouldAddNewPatient() throws Exception {
        Patient patient = new Patient(1L,"John", "Doe", new Date(1990-01-01), "12345", 'M', "test");

        mockMvc.perform(post("/patient/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patient)))
                .andExpect(status().isOk());

        verify(patientService, times(1)).addPatient(any());
    }

    @Test
    public void deletePatient_shouldDeletePatient() throws Exception {
        Long id = 1L;
        doNothing().when(patientService).deletePatient(id);

        mockMvc.perform((RequestBuilder) delete("/patient/delete/1"))
                .andExpect(status().isOk());

        verify(patientService, times(1)).deletePatient(id);
    }

    @Test
    public void updatePatient_shouldUpdatePatient() throws Exception {
        Patient patient = new Patient(1L, "John", "Doe", new Date(1990-01-01), "12345", 'M', "test");
        doNothing().when(patientService).updatePatient(patient);

        mockMvc.perform(put("/patient/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patient)))
                .andExpect(status().isOk());

        verify(patientService, times(1)).updatePatient(any());
    }
}
