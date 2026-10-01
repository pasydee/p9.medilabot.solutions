package medilabo.solutions.notes.service;

import medilabo.solutions.notes.model.Note;
import medilabo.solutions.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotesByPatientId(Integer patientId) {
        return noteRepository.findByPatientIdOrderByDateDesc(patientId);
    }

    public Note addNote(Integer patientId, String content) {
        Note note = new Note();
        note.setPatientId(patientId);
        note.setDate(LocalDateTime.now());
        note.setContent(content);
        return noteRepository.save(note);
    }

    public Note updateNote(String noteId, String content) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Note introuvable avec l'id : " + noteId));
        note.setContent(content);
        note.setDate(LocalDateTime.now()); // met à jour la date de dernière modif
        return noteRepository.save(note);
    }

    public void deleteNote(String noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Note introuvable avec l'id : " + noteId);
        }
        noteRepository.deleteById(noteId);
    }

}