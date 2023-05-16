package com.mediscreen.note.service;

import com.mediscreen.note.bean.PatientBean;
import com.mediscreen.note.proxy.PatientProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class AgeCalculatorServiceTests {
    @Mock
    private PatientProxy patientProxy;
    @InjectMocks
    private AgeCalculatorService ageCalculatorService;

    @Test
    public void testGetAge() {
        // GIVEN
        PatientBean patient = new PatientBean();
        patient.setDob(LocalDate.of(2000, 1, 1));
        patient.setId(1L);
        // WHEN
        when(patientProxy.getPatientById(1L)).thenReturn(patient);
        // THEN
        int age = ageCalculatorService.getAge(1L);
        assertEquals(23, age);
    }
}
