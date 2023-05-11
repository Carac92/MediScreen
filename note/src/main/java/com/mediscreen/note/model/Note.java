package com.mediscreen.note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;
    private String patientId;
    private String annotation;

    public Note() {
    }

    public Note(String id, String patientId, String annotation) {
        this.id = id;
        this.patientId = patientId;
        this.annotation = annotation;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
