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
    public String addNote(@ModelAttribute NoteBean note){
        noteProxy.addNote(note);
        return "redirect:/patient/"+note.getPatId();
    }
    @GetMapping("/{id}/addNote")
    public String addNoteForm(@PathVariable(value = "id") Long patId, Model model){
        NoteBean note = new NoteBean();
        note.setPatId(patId.toString());
        model.addAttribute("note", note);
        return "addNoteForm";
    }
    @PostMapping("/deleteNote")
    public String deleteNoteById(@RequestParam(value = "noteId") String noteId, @RequestParam(value = "patId") String patId){
        noteProxy.deleteNoteById(noteId);
        return "redirect:/patient/" + patId;
    }
    @PostMapping("/updateNote")
    public String updateNoteById(@ModelAttribute NoteBean note){
        noteProxy.updateNote(note);
        return "redirect:/patient/"+note.getPatId();
    }
}
