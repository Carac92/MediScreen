package com.mediscreen.note.service;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private NoteService noteService;

    @Test
    public void testGetNoteById() {
        Note note = new Note("1", "1","test");
        when(noteRepository.findById("1")).thenReturn(Optional.of(note));

        Note result = noteService.findById("1");

        assertEquals(note, result);
    }
    @Test
    public void testGetNoteByPatId() {
        List<Note> notes = List.of(new Note("1", "1", "test"));
        when(noteRepository.findByPatId("1")).thenReturn(notes);

        List<Note> result = noteService.findAllNotesByPatId("1");

        assertEquals(notes, result);
    }
    @Test
    public void testGetAllNotes() {
        List<Note> notes = List.of(new Note("1", "1", "test"));
        when(noteRepository.findAll()).thenReturn(notes);

        List<Note> result = noteService.findAll();

        assertEquals(notes, result);
    }
    @Test
    public void testCreateNote() {
        Note note = new Note();
        note.setPatId("1");
        note.setAnnotation("test");

        noteService.add(note);

        verify(noteRepository, times(1)).save(note);
    }
    @Test
    public void testUpdateNote() {
        Note note = new Note();
        note.setId("1");
        note.setPatId("1");
        note.setAnnotation("test");

        noteService.update(note);

        verify(noteRepository, times(1)).save(note);
    }
    @Test
    public void testDeleteNote() {
        noteService.deleteById("1");

        verify(noteRepository, times(1)).deleteById("1");
    }
    @Test
    public void testDeleteAllNotes() {
        noteService.deleteAll();

        verify(noteRepository, times(1)).deleteAll();
    }
}
