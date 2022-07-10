package com.example.notepad.service;

import com.example.notepad.model.Note;
import com.example.notepad.model.User;
import com.example.notepad.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Note findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveNote(Note note) {
        repository.save(note);
    }

    @Override
    public void updateNote(Long id, String title, String content, boolean checkbox) {
        Optional<Note> optionalNote = repository.findById(id);
        Note updated = optionalNote.get();
        updated.setTitle(title);
        updated.setContent(content);
        updated.setPrivacy(checkbox);
        updated.setLastUpdate(new Date());
        repository.save(updated);
    }

    @Override
    public void deleteNote(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Note> findAllByAuthor(User author) {
        return repository.findAllByAuthor(author);
    }

    @Override
    public List<Note> findAllByPrivacy(boolean privacy) {
        return repository.findAllByPrivacy(privacy);
    }
}
