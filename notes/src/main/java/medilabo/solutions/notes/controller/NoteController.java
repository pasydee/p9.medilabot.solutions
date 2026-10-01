package medilabo.solutions.notes.controller;

import medilabo.solutions.notes.dto.NoteRequest;
import medilabo.solutions.notes.model.Note;
import medilabo.solutions.notes.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{patientId}")
    public List<Note> getNotes(@PathVariable Integer patientId) {
        return noteService.getNotesByPatientId(patientId);
    }

    @PostMapping("/{patientId}")
    public Note addNote(@PathVariable Integer patientId,
                        @RequestBody NoteRequest request) {
        return noteService.addNote(patientId, request.getContent());
    }

    @PutMapping("/{noteId}")
    public Note updateNote(@PathVariable String noteId,
                           @RequestBody NoteRequest request) {
        return noteService.updateNote(noteId, request.getContent());
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable String noteId) {
        noteService.deleteNote(noteId);
    }

}