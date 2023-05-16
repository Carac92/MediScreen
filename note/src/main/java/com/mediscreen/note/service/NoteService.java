package com.mediscreen.note.service;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void deleteAll() {
        log.info("Deleting all notes");
        noteRepository.deleteAll();
    }
    public void deleteById(String id) {
        log.info("Deleting note with id: {}", id);
        noteRepository.deleteById(id);
    }
    public void add(Note note) {
        note.setDate(LocalDateTime.now());
        log.info("Adding note: {}", note);
        noteRepository.save(note);
    }
    public void update(Note note) {
        log.info("Updating note: {}", note);
        noteRepository.save(note);
    }
    public Note findById(String id){
        log.info("Finding note with id: {}", id);
        Optional<Note> note = noteRepository.findById(id);
        return note.orElseGet(Note::new);
    }
    public List<Note> findAll() {
        log.info("Finding all notes");
        return noteRepository.findAll();
    }
    public List<Note> findAllNotesByPatId(String patId) {
        log.info("Finding all notes by patId: {}", patId);
        return noteRepository.findByPatId(patId);
    }
}
