package com.mediscreen.note.service;

import com.mediscreen.note.bean.PatientBean;
import com.mediscreen.note.proxy.PatientProxy;
import org.springframework.stereotype.Service;

import java.time.Period;
/**
 * This is the service for the age calculator.
 * It is used to calculate the age of a patient.
 */
@Service
public class AgeCalculatorService {

    private final PatientProxy patientProxy;

    public AgeCalculatorService(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }
    public int getAge(Long id) {
        PatientBean patient =patientProxy.getPatientById(id);
        int age = Period.between(patient.getDob(), java.time.LocalDate.now()).getYears();
        if(age<=0){
            throw new IllegalArgumentException("Age can't be negative");
        }
        return age;
    }

}
