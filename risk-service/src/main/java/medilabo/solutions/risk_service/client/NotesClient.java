package medilabo.solutions.risk_service.client;

import medilabo.solutions.risk_service.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "notes-service",
        url = "${NOTES_SERVICE_URL}"
)
public interface NotesClient {

    @GetMapping("/notes/{patientId}")
    List<Note> getNotes(@PathVariable Long patientId);
}

