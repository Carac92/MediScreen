package com.mediscreen.note.service;

import com.mediscreen.note.constant.Assessment;
import com.mediscreen.note.proxy.PatientProxy;
import org.springframework.stereotype.Service;

@Service
public class DiabetesAssessmentService {
    private final TriggersCalculator triggersCalculator;
    private final AgeCalculator ageCalculator;
    private final PatientProxy patientProxy;

    public DiabetesAssessmentService(TriggersCalculator triggersCalculator,
                                     AgeCalculator ageCalculator,
                                     PatientProxy patientProxy) {
        this.triggersCalculator = triggersCalculator;
        this.ageCalculator = ageCalculator;
        this.patientProxy = patientProxy;
    }

    public Assessment getAssessment(String patId) {
        int triggersCount = triggersCalculator.getTriggersCount(patId);
        int age = ageCalculator.getAge(Long.parseLong(patId));
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
