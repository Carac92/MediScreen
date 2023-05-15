package com.mediscreen.note.service;

import com.mediscreen.note.bean.PatientBean;
import com.mediscreen.note.constant.Assessment;
import com.mediscreen.note.proxy.PatientProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class DiabetesAssessmentServiceTest {
    @Mock
    private PatientProxy patientProxy;
    @Mock
    private TriggersCalculator triggersCalculator;
    @Mock
    private AgeCalculator ageCalculator;
    @InjectMocks
    private DiabetesAssessmentService diabetesAssessmentService;

    @Test
    public void testGetAssessmentWithMaleUnder30AndTriggersLessThen2_ShouldReturnNone() {
        //GIVEN
        PatientBean patientBean = new PatientBean();
        patientBean.setGender('M');
        patientBean.setId(1L);
        //WHEN
        when(ageCalculator.getAge(1L)).thenReturn(20);
        when(patientProxy.getPatientById(1L)).thenReturn(patientBean);
        when(triggersCalculator.getTriggersCount("1")).thenReturn(2);
        //THEN
        Assessment result = diabetesAssessmentService.getAssessment("1");
        assertEquals(result.getAssessment(),Assessment.NONE.getAssessment());
    }
    @Test
    public void testGetAssessmentWithPersonMore30And2Triggers_ShouldReturnInDanger(){
        //GIVEN
        PatientBean patientBean = new PatientBean();
        patientBean.setGender('M');
        patientBean.setId(1L);
        //WHEN
        when(ageCalculator.getAge(1L)).thenReturn(31);
        when(patientProxy.getPatientById(1L)).thenReturn(patientBean);
        when(triggersCalculator.getTriggersCount("1")).thenReturn(2);
        //THEN
        Assessment result = diabetesAssessmentService.getAssessment("1");
        assertEquals(result.getAssessment(),Assessment.BORDERLINE.getAssessment());
    }

    @Test
    public void testGetAssessmentWithMaleUnder30And4Triggers_ShouldReturnInDanger(){
        //GIVEN
        PatientBean patientBean = new PatientBean();
        patientBean.setGender('M');
        patientBean.setId(1L);
        //WHEN
        when(ageCalculator.getAge(1L)).thenReturn(20);
        when(patientProxy.getPatientById(1L)).thenReturn(patientBean);
        when(triggersCalculator.getTriggersCount("1")).thenReturn(4);
        //THEN
        Assessment result = diabetesAssessmentService.getAssessment("1");
        assertEquals(result.getAssessment(),Assessment.IN_DANGER.getAssessment());
    }
    @Test
    public void testGetAssessmentWithFemaleUnder30And5Triggers_ShouldReturnInDanger(){
        //GIVEN
        PatientBean patientBean = new PatientBean();
        patientBean.setGender('F');
        patientBean.setId(1L);
        //WHEN
        when(ageCalculator.getAge(1L)).thenReturn(20);
        when(patientProxy.getPatientById(1L)).thenReturn(patientBean);
        when(triggersCalculator.getTriggersCount("1")).thenReturn(5);
        //THEN
        Assessment result = diabetesAssessmentService.getAssessment("1");
        assertEquals(result.getAssessment(),Assessment.IN_DANGER.getAssessment());
    }
    @Test
    public void testGetAssessmentWithPersonMore30And7Triggers(){
        //GIVEN
        PatientBean patientBean = new PatientBean();
        patientBean.setGender('M');
        patientBean.setId(1L);
        //WHEN
        when(ageCalculator.getAge(1L)).thenReturn(31);
        when(patientProxy.getPatientById(1L)).thenReturn(patientBean);
        when(triggersCalculator.getTriggersCount("1")).thenReturn(7);
        //THEN
        Assessment result = diabetesAssessmentService.getAssessment("1");
        assertEquals(result.getAssessment(),Assessment.IN_DANGER.getAssessment());
    }

    @Test
    public void testGetAssessmentWithFemaleUnder30And8TriggersCount_ShouldReturnEarlyOnSet(){
        //GIVEN
        PatientBean patientBean = new PatientBean();
        patientBean.setGender('F');
        patientBean.setId(1L);
        //WHEN
        when(ageCalculator.getAge(1L)).thenReturn(20);
        when(patientProxy.getPatientById(1L)).thenReturn(patientBean);
        when(triggersCalculator.getTriggersCount("1")).thenReturn(8);
        //THEN
        Assessment result = diabetesAssessmentService.getAssessment("1");
        assertEquals(result.getAssessment(),Assessment.EARLY_ONSET.getAssessment());
    }
    @Test
    public void testGetAssessmentWithMaleUnder30And6Triggers_ShouldReturnEarlyOnSet(){
        //GIVEN
        PatientBean patientBean = new PatientBean();
        patientBean.setGender('M');
        patientBean.setId(1L);
        //WHEN
        when(ageCalculator.getAge(1L)).thenReturn(20);
        when(patientProxy.getPatientById(1L)).thenReturn(patientBean);
        when(triggersCalculator.getTriggersCount("1")).thenReturn(6);
        //THEN
        Assessment result = diabetesAssessmentService.getAssessment("1");
        assertEquals(result.getAssessment(),Assessment.EARLY_ONSET.getAssessment());
    }

    @Test
    public void testGetAssessmentWithPersonMore30And10Triggers_ShouldReturnEarlyOnSet(){
        //GIVEN
        PatientBean patientBean = new PatientBean();
        patientBean.setGender('M');
        patientBean.setId(1L);
        //WHEN
        when(ageCalculator.getAge(1L)).thenReturn(31);
        when(patientProxy.getPatientById(1L)).thenReturn(patientBean);
        when(triggersCalculator.getTriggersCount("1")).thenReturn(10);
        //THEN
        Assessment result = diabetesAssessmentService.getAssessment("1");
        assertEquals(result.getAssessment(),Assessment.EARLY_ONSET.getAssessment());
    }

}
