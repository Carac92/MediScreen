package com.mediscreen.ui.controller;

import com.mediscreen.ui.beans.PatientBean;
import com.mediscreen.ui.proxies.AssessmentProxy;
import com.mediscreen.ui.proxies.NoteProxy;
import com.mediscreen.ui.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;
    private final AssessmentProxy assessmentProxy;

    public PatientController(PatientProxy patientProxy, NoteProxy noteProxy, AssessmentProxy assessmentProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
        this.assessmentProxy = assessmentProxy;
    }
// Patient endpoints
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
        model.addAttribute("notes", noteProxy.getNotesByPatId(id.toString()));
        //TODO: Regarder pourquoi le charactère n'est pas affiché correctement
        String htmlGender = "&#x" + Integer.toHexString(String.valueOf(patient.getGender()).codePointAt(0)).toUpperCase() + ";";
        model.addAttribute("gender", htmlGender);
        return "patient";
    }
    @GetMapping("/{firstName}/{lastName}")
    public String getPatientByFirstNameAndLastName(@PathVariable(value = "firstName") String firstName,
                                                   @PathVariable(value = "lastName") String lastName, Model model) {
        PatientBean patient = patientProxy.getPatientByFirstNameAndLastName(firstName, lastName);
        model.addAttribute("patient", patient);
        return "patient";
    }
    @PostMapping("/updatePatient")
    public String updatePatient(PatientBean patient) {
        patientProxy.updatePatient(patient.getId(),patient);
        return "redirect:/list";
    }
    @PostMapping("/validAddPatient")
    public String addPatient(PatientBean patient) {
        patientProxy.addPatient(patient);
        return "redirect:/patient/list";
    }
    @GetMapping("/addPatient")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new PatientBean());
        return "addPatientForm";
    }
    @PostMapping("/deletePatient")
    public String deletePatient(@RequestParam(value = "patientId") Long patientId) {
        patientProxy.deletePatient(patientId);
        return "redirect:/patient/list";
    }
    @GetMapping("/{id}/assessment")
    public String getAssessment(@PathVariable("id") Long id, Model model) {
        model.addAttribute("assessment", assessmentProxy.getAssessment(id.toString()));
        return "assessment";
    }
}
