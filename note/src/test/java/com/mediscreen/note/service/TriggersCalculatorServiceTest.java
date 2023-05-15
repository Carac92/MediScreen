package com.mediscreen.note.service;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class TriggersCalculatorServiceTest {

    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private TriggersCalculator triggersCalculator;

    @Test
    public void getTriggersCount() {
        // GIVEN
        List<Note> notes = List.of(
                new Note("1", "1", "Hémoglobine A1C, Fumeur, Anormal, Fumeur, Anormal")
        );
        // WHEN
        when(noteRepository.findByPatId("1")).thenReturn(notes);
        // THEN
        int result = triggersCalculator.getTriggersCount("1");
        assertEquals(3, result);
    }

}
