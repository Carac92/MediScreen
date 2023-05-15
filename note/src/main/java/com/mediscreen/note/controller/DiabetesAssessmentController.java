package com.mediscreen.note.controller;

import com.mediscreen.note.service.DiabetesAssessmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiabetesAssessmentController {
    private final DiabetesAssessmentService diabetesAssessmentService;

    public DiabetesAssessmentController(DiabetesAssessmentService diabetesAssessmentService) {
        this.diabetesAssessmentService = diabetesAssessmentService;
    }
    @GetMapping
    public String getAssessment(String patId) {
        return diabetesAssessmentService.getAssessment(patId).toString();
    }
}
