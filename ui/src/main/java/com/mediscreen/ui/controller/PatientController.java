package com.mediscreen.ui.controller;

import com.mediscreen.ui.beans.PatientBean;
import com.mediscreen.ui.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientProxy patientProxy;

    public PatientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @GetMapping("/list")
    public String listPatients(Model model) {
        List<PatientBean> patients = patientProxy.getPatients();
        model.addAttribute("patients", patients);
        return "list";
    }
    @GetMapping("/{id}")
    public String getPatientById(@PathVariable("id") Long id, Model model) {
        PatientBean patient = patientProxy.getPatientById(id);
        model.addAttribute("patient", patient);
        //TODO: Regarder pourquoi le charactère n'est pas affiché correctement
        String htmlGender = "&#x" + Integer.toHexString(String.valueOf(patient.getGender()).codePointAt(0)).toUpperCase() + ";";
        model.addAttribute("gender", htmlGender);
        return "show";
    }
    @GetMapping("/{firstName}/{lastName}")
    public String getPatientByFirstNameAndLastName(@PathVariable(value = "firstName") String firstName,
                                                   @PathVariable(value = "lastName") String lastName, Model model) {
        PatientBean patient = patientProxy.getPatientByFirstNameAndLastName(firstName, lastName);
        model.addAttribute("patient", patient);
        return "show";
    }
    @PostMapping("/updatePatient")
    public String updatePatient(PatientBean patient) {
        patientProxy.updatePatient(patient);
        return "redirect:/list";
    }
    @PostMapping("/addPatient")
    public String addPatient(PatientBean patient) {
        patientProxy.addPatient(patient);
        return "redirect:/list";
    }
    @GetMapping("/add")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new PatientBean());
        return "addPatientForm";
    }
    @PostMapping("/deletePatient")
    public String deletePatient(Long patient) {
        patientProxy.deletePatient(patient);
        return "redirect:/list";
    }

}
