package medilabo.solutions.front.client;

import medilabo.solutions.front.config.FeignClientConfig;
import medilabo.solutions.front.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "notes-service",
        url = "${notes.service.url}",
        configuration = FeignClientConfig.class
)
public interface NotesClient {

    @GetMapping("/notes/{patientId}")
    List<Note> getNotes(@PathVariable Long patientId);

    @PostMapping("/notes/{patientId}")
    Note addNote(@PathVariable Long patientId, @RequestBody Map<String, String> body);

    @PutMapping("/notes/{noteId}")
    Note updateNote(@PathVariable String noteId, @RequestBody Map<String, String> body);

    @DeleteMapping("/notes/{noteId}")
    void deleteNote(@PathVariable String noteId);
}