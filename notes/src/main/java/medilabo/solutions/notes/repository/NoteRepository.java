package medilabo.solutions.notes.repository;

import medilabo.solutions.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findByPatientIdOrderByDateDesc(Integer patientId);

}
