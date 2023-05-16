package com.mediscreen.note.controller;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all notes")
    public List<Note> listNotes() {
        return noteService.findAll();
    }
    @GetMapping("/list/{patId}")
    @Operation(summary = "Get all notes of a patient")
    public List<Note> listNotesByPatId(@PathVariable String patId) {
        return noteService.findAllNotesByPatId(patId);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a note by its id")
    public Note getNoteById(@PathVariable String id) {
        return noteService.findById(id);
    }
    @PostMapping("/add")
    @Operation(summary = "Add a note")
    public void addNote(@RequestBody Note note) {
        noteService.add(note);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a note by its id")
    public void deleteNoteById(@PathVariable String id) {
        noteService.deleteById(id);
    }
    @PutMapping("/update")
    @Operation(summary = "Update a note")
    public void updateNoteById(@RequestBody Note note) {
        noteService.update(note);
    }
    @DeleteMapping("/deleteAll")
    @Operation(summary = "Delete all notes")
    public void deleteAllNotes() {
        noteService.deleteAll();
    }
}
