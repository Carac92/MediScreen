package com.mediscreen.note.constant;
/**
 * This is the class for the assessment.
 * It is used to store the assessment.
 */
public enum Assessment {
    NONE("None"),
    BORDERLINE("Borderline"),
    IN_DANGER("In Danger"),
    EARLY_ONSET("Early Onset");

    private final String assessment;

    Assessment(String assessment) {
        this.assessment = assessment;
    }

    public String getAssessment() {
        return assessment;
    }
}
