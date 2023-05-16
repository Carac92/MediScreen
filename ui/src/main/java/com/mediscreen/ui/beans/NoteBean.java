package com.mediscreen.ui.beans;

import java.time.LocalDateTime;
/**
 * This is the bean for the note.
 */
public class NoteBean {
    private String id;
    private LocalDateTime date;
    private String patId;
    private String annotation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
