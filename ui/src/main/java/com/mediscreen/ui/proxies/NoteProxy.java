package com.mediscreen.ui.proxies;

import com.mediscreen.ui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * This is the proxy for the note api.
 */
@FeignClient(name = "note", url = "${noteUrl}")
public interface NoteProxy {
    @GetMapping(value = "/list")
    List<NoteBean> getNotes();
    @GetMapping(value = "/list/{patId}")
    List<NoteBean> getNotesByPatId(@PathVariable String patId);
    @GetMapping(value = "/{id}")
    NoteBean getNoteById(@PathVariable String id);
    @PostMapping(value = "/add")
    void addNote(NoteBean note);
    @PutMapping(value = "/update")
    void updateNote(NoteBean note);

    @DeleteMapping(value = "/delete/{id}")
    void deleteNoteById(@PathVariable String id);
    @DeleteMapping(value = "/deleteAll")
    void deleteAllNotes();
}
