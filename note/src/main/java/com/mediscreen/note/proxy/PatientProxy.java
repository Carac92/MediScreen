package com.mediscreen.note.proxy;

import com.mediscreen.note.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * This is the proxy for the patient service.
 */

@FeignClient(name = "patient-service", url = "${patientUrl}")
public interface PatientProxy {
    @GetMapping(value = "/list")
    List<PatientBean> getPatients();
    @GetMapping(value = "/{id}")
    PatientBean getPatientById(@PathVariable("id") Long id);

}
