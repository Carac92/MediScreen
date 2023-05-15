package com.mediscreen.note.controller;

import com.mediscreen.note.service.DiabetesAssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/assess")
public class DiabetesAssessmentController {
    private final DiabetesAssessmentService diabetesAssessmentService;

    public DiabetesAssessmentController(DiabetesAssessmentService diabetesAssessmentService) {
        this.diabetesAssessmentService = diabetesAssessmentService;
    }
    @GetMapping("diabetes/{patId}")
    public String getAssessment(@PathVariable String patId) {
        return diabetesAssessmentService.getAssessment(patId).getAssessment();
    }
}
