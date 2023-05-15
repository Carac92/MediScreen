package com.mediscreen.note.service;

import com.mediscreen.note.constant.Triggers;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TriggersCalculatorService {
    private final NoteRepository noteRepository;

    public TriggersCalculatorService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public int getTriggersCount(String patId) {
        List<String> triggers = new ArrayList<>(Triggers.getTrigger());
        List<Note> notes = noteRepository.findByPatId(patId);
        int count = 0;
        for (Note note : notes) {
            List<String> triggersToRemove = new ArrayList<>();
            for (String trigger : triggers) {
                if (note.getAnnotation().contains(trigger)) {
                    count++;
                    triggersToRemove.add(trigger);
                }
            }
            triggers.removeAll(triggersToRemove);
        }
        return count;
    }
}
