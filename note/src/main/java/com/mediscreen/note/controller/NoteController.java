package com.mediscreen.note.controller;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public List<Note> listNotes() {
        return noteService.findAll();
    }
    @GetMapping("/list/{patId}")
    public List<Note> listNotesByPatId(@PathVariable String patId) {
        return noteService.findAllNotesByPatId(patId);
    }
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable String id) {
        return noteService.findById(id);
    }
    @PostMapping("/add")
    public void addNote(@RequestBody Note note) {
        noteService.add(note);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteNoteById(@PathVariable String id) {
        noteService.deleteById(id);
    }
    @PutMapping("/update")
    public void updateNoteById(@RequestBody Note note) {
        noteService.update(note);
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllNotes() {
        noteService.deleteAll();
    }
}
