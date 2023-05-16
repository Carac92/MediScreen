package com.mediscreen.note.controller;

import com.mediscreen.note.service.DiabetesAssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assess")
public class DiabetesAssessmentController {
    private final DiabetesAssessmentService diabetesAssessmentService;

    public DiabetesAssessmentController(DiabetesAssessmentService diabetesAssessmentService) {
        this.diabetesAssessmentService = diabetesAssessmentService;
    }
    @GetMapping("/diabetes/{patId}")
    @Operation(summary = "Get the diabetes assessment of a patient")
    public String getAssessment(@PathVariable String patId) {
        return diabetesAssessmentService.getAssessment(patId).getAssessment();
    }
}
