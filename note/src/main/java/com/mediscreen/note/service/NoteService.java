package com.mediscreen.note.service;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void deleteAll() {
        noteRepository.deleteAll();
    }
    public void deleteById(String id) {
        noteRepository.deleteById(id);
    }
    public void add(Note note) {
        note.setDate(LocalDateTime.now());
        noteRepository.save(note);
    }
    public void update(Note note) {
        noteRepository.save(note);
    }
    public Note findById(String id) {
        return noteRepository.findById(id).orElse(null);
    }
    public List<Note> findAll() {
        return noteRepository.findAll();
    }
    public List<Note> findAllNotesByPatId(String patId) {
        return noteRepository.findByPatId(patId);
    }
}
