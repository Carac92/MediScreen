package com.mediscreen.note.proxy;

import com.mediscreen.note.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient-service", url = "http://localhost:8081/patient")
public interface PatientProxy {
    @GetMapping(value = "/list")
    List<PatientBean> getPatients();
    @GetMapping(value = "/{id}")
    PatientBean getPatientById(@PathVariable("id") Long id);
    @GetMapping(value = "/{firstName}/{lastName}")
    PatientBean getPatientByFirstNameAndLastName(@PathVariable(value = "firstName") String firstName,
                                                 @PathVariable(value = "lastName") String lastName);
    @PostMapping(value = "/add")
    void addPatient(PatientBean patient);
    @DeleteMapping(value = "/delete/{id}")
    void deletePatient(@PathVariable(value = "id") Long id);
    @PutMapping(value = "/update/{id}")
    void updatePatient(@PathVariable Long id, PatientBean patient);

}
