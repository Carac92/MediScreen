package com.mediscreen.ui.controller;

import com.mediscreen.ui.beans.NoteBean;
import com.mediscreen.ui.proxies.NoteProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteProxy noteProxy;

    public NoteController(NoteProxy noteProxy) {
        this.noteProxy = noteProxy;
    }
    @GetMapping("/{id}")
    public String getNoteById(@PathVariable("id") String id, Model model) {
        model.addAttribute("note", noteProxy.getNoteById(id));
        return "note";
    }
    @PostMapping("/addNote")
    public String addNote(NoteBean note){
        noteProxy.addNote(note);
        return "redirect:patient/${note.patId}";
    }
    @PostMapping("/deleteNote")
    public String deleteNoteById(@RequestParam(value = "noteId") String noteId){
        noteProxy.deleteNoteById(noteId);
        return "redirect:patient/${note.patId)}";
    }
}
