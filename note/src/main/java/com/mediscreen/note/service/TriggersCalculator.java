package com.mediscreen.note.service;

import com.mediscreen.note.constant.Triggers;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriggersCalculator {
    private final NoteRepository noteRepository;

    public TriggersCalculator(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public int getTriggersCount(String patId) {
        List<String> triggers = Triggers.getTrigger();
        List<Note> notes = noteRepository.findByPatId(patId);
        int count = 0;
        for (Note note : notes) {
            for (String trigger : triggers) {
                if (note.getAnnotation().contains(trigger)) {
                    count++;
                }
            }
        }
        return count;
    }
}
