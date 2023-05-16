package com.mediscreen.note.service;

import com.mediscreen.note.constant.Assessment;
import com.mediscreen.note.proxy.PatientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
/**
 * This is the service for the diabetes assessment.
 * It is used to calculate the diabetes assessment of a patient.
 * It uses the triggers calculator service and the age calculator service.
 * It uses the patient proxy to communicate with the patient microservice.
 */
@Slf4j
@Service
public class DiabetesAssessmentService {
    private final TriggersCalculatorService triggersCalculatorService;
    private final AgeCalculatorService ageCalculatorService;
    private final PatientProxy patientProxy;

    public DiabetesAssessmentService(TriggersCalculatorService triggersCalculatorService,
                                     AgeCalculatorService ageCalculatorService,
                                     PatientProxy patientProxy) {
        this.triggersCalculatorService = triggersCalculatorService;
        this.ageCalculatorService = ageCalculatorService;
        this.patientProxy = patientProxy;
    }

    public Assessment getAssessment(String patId) {
        log.info("Calculating diabetes assessment of patient with id: {}", patId);
        int triggersCount = triggersCalculatorService.getTriggersCount(patId);
        int age = ageCalculatorService.getAge(Long.parseLong(patId));
        char gender = patientProxy.getPatientById(Long.parseLong(patId)).getGender();
        if(gender == 'M' && age<30 && triggersCount>=5){
            return Assessment.EARLY_ONSET;
        } else if (age>=30 && triggersCount>=8) {
            return Assessment.EARLY_ONSET;
        } else if (gender == 'F' && age<30 && triggersCount>=7) {
            return Assessment.EARLY_ONSET;
        } else if (age>=30 && triggersCount>=6) {
            return Assessment.IN_DANGER;
        } else if (gender == 'M' && age<30 && triggersCount>=3 && triggersCount<5) {
            return Assessment.IN_DANGER;
        } else if (gender == 'F' && age<30 && triggersCount>=4 && triggersCount<7) {
            return Assessment.IN_DANGER;
        } else if (age>30 && triggersCount>=2) {
            return Assessment.BORDERLINE;
        } else {
            return Assessment.NONE;
        }
    }
}
