package com.mediscreen.note.repository;

import com.mediscreen.note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * This is the repository for the note.
 * It extends MongoRepository to use the methods of MongoRepository.
 * It is used to communicate with MongoDB database.
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByPatId(String patId);
}
