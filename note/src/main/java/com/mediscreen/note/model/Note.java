package com.mediscreen.note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;
    private String patId;
    private LocalDateTime date;
    private String annotation;

    public Note() {
    }

    public Note(String id, String patientId, String annotation) {
        this.id = id;
        this.patId = patientId;
        this.annotation = annotation;
    }

    public String getId() {
        return id;
    }

    public String getPatId() {
        return patId;
    }

    public String getAnnotation() {
        return annotation;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
