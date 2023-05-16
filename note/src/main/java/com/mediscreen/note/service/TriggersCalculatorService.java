package com.mediscreen.note.service;

import com.mediscreen.note.constant.Triggers;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * This is the service for the triggers.
 * It is used to calculate the number of triggers in the notes.
 * the algorithm avoid to count twice the same trigger in the same note or in another note.
 * It communicates with the repository.
 */
@Slf4j
@Service
public class TriggersCalculatorService {
    private final NoteRepository noteRepository;

    public TriggersCalculatorService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public int getTriggersCount(String patId) {
        log.info("Calculating triggers count for patient with id: {}", patId);
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
