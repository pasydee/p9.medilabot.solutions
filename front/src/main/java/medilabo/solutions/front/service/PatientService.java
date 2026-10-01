package medilabo.solutions.front.service;

import feign.FeignException;
import medilabo.solutions.front.client.PatientClient;
import medilabo.solutions.front.client.NotesClient;
import medilabo.solutions.front.model.Note;
import medilabo.solutions.front.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class PatientService {

    private final PatientClient patientClient;
    private final NotesClient notesClient;

    private static final Pattern GENRE_PATTERN = Pattern.compile("F|M");
    private static final Pattern TEL_PATTERN = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");

    public PatientService(PatientClient patientClient, NotesClient notesClient) {
        this.patientClient = patientClient;
        this.notesClient = notesClient;
    }

    private void validatePatientData(Patient patient) {
        if (patient.getGenre() == null || !GENRE_PATTERN.matcher(patient.getGenre()).matches()) {
            throw new PatientOperationException("Le genre doit être 'F' ou 'M'.");
        }
        if (patient.getTelephone() != null && !patient.getTelephone().isBlank()
                && !TEL_PATTERN.matcher(patient.getTelephone()).matches()) {
            throw new PatientOperationException("Numéro de téléphone invalide (format attendu : 100-222-3333).");
        }
    }

    public Patient createPatient(Patient patient) {
        try {
            return patientClient.createPatient(patient);
        } catch (FeignException.BadRequest e) {
            throw new PatientOperationException(
                    "Données invalides : vérifiez les champs saisis (notamment la date de naissance, format AAAA-MM-JJ).");
        }
    }

    public List<Patient> getAllPatients() {
        return patientClient.getAllPatients();
    }

    public Patient searchPatient(String prenom, String nom, String dateNaissance) {
        try {
            return patientClient.searchPatient(prenom, nom, dateNaissance);
        } catch (FeignException.NotFound e) {
            return null;
        } catch (FeignException.BadRequest e) {
            throw new PatientOperationException(
                    "Date de naissance invalide, format attendu AAAA-MM-JJ.");
        }
    }

    public void updatePatient(Long id, Patient patient) {
        validatePatientData(patient);
        try {
            patientClient.updatePatient(id, patient);
        } catch (FeignException.NotFound e) {
            throw new PatientOperationException("Patient introuvable (id " + id + ").");
        } catch (FeignException.BadRequest e) {
            throw new PatientOperationException(
                    "Données invalides : vérifiez les champs saisis (notamment la date de naissance, format AAAA-MM-JJ).");
        }
    }

    public Patient getPatientById(Long id) {
        try {
            return patientClient.getPatientById(id);
        } catch (FeignException.NotFound e) {
            return null;
        }
    }

    public List<Note> getNotes(Long patientId) {
        return notesClient.getNotes(patientId);
    }

    public Note addNote(Long patientId, String content) {
        Map<String, String> body = Map.of("content", content);
        return notesClient.addNote(patientId, body);
    }

    public Note updateNote(String noteId, String content) {
        Map<String, String> body = Map.of("content", content);
        try {
            return notesClient.updateNote(noteId, body);
        } catch (FeignException.NotFound e) {
            throw new PatientOperationException("Note introuvable.");
        }
    }

    public void deleteNote(String noteId) {
        try {
            notesClient.deleteNote(noteId);
        } catch (FeignException.NotFound e) {
            throw new PatientOperationException("Note introuvable.");
        }
    }
}