package com.mediscreen.note.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NoteService noteService;

    @Test
    public void testGetNoteById_shouldReturnNote() throws Exception {
        Note note = new Note("1", "1", "test");
        given(noteService.findById("1")).willReturn(note);

        mockMvc.perform(get("/note/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.annotation").value("test"));
    }
    @Test
    public void testGetNoteByPatId_shouldReturnListOfNotes() throws Exception {
        List<Note> notes = List.of(new Note ("1", "1", "test"));
        given(noteService.findAllNotesByPatId("1")).willReturn(notes);

        mockMvc.perform(get("/note/list/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].annotation").value("test"));
    }
    @Test
    public void testGetNoteByPatId_shouldReturnEmptyList() throws Exception {
        List<Note> notes = List.of();
        given(noteService.findAllNotesByPatId("1")).willReturn(notes);

        mockMvc.perform(get("/note/list/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
    @Test
    public void testGetAllNotes_ShouldReturnListOfNotes() throws Exception {
        List<Note> notes = List.of(new Note ("1", "1", "test"));
        given(noteService.findAll()).willReturn(notes);

        mockMvc.perform(get("/note/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].annotation").value("test"));
    }
    @Test
    public void testAddNote_shouldReturnStatusOk() throws Exception {
        Note note = new Note ("1", "1", "test");

        mockMvc.perform(post("/note/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(note)))
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteNoteById_shouldReturnStatusOk() throws Exception {
        mockMvc.perform(delete("/note/delete/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateNoteById_shouldReturnStatusOk() throws Exception {
        Note note = new Note ("1", "1", "test");

        mockMvc.perform(put("/note/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(note)))
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteAllNotes_shouldReturnStatusOk() throws Exception {
        mockMvc.perform(delete("/note/deleteAll"))
                .andExpect(status().isOk());
    }
}
