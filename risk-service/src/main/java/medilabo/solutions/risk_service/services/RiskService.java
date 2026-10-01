package medilabo.solutions.risk_service.services;

import medilabo.solutions.risk_service.client.PatientClient;
import medilabo.solutions.risk_service.client.NotesClient;
import medilabo.solutions.risk_service.model.Note;
import medilabo.solutions.risk_service.model.Patient;
import medilabo.solutions.risk_service.model.RiskLevel;
import medilabo.solutions.risk_service.model.RiskResponse;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiskService {

    private final PatientClient patientClient;
    private final NotesClient notesClient;

    public RiskService(PatientClient patientClient, NotesClient notesClient) {
        this.patientClient = patientClient;
        this.notesClient = notesClient;
    }

    private static final List<String> TRIGGERS = List.of(
            "hémoglobine a1c",
            "microalbumine",
            "taille",
            "poids",
            "fumeur",
            "fumeuse",
            "anormal",
            "cholestérol",
            "vertiges",
            "rechute",
            "réaction",
            "anticorps"
    );

    public int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public int countTriggers(List<Note> notes) {

        String allText = notes.stream()
                .map(Note::getContent)
                .collect(Collectors.joining(" "))
                .toLowerCase();

        int count = 0;

        for (String trigger : TRIGGERS) {
            if (allText.contains(trigger.toLowerCase())) {
                count++;
            }
        }

        return count;
    }

    public RiskLevel calculateRisk(int age, String gender, int triggerCount) {

        if (triggerCount == 0) {
            return RiskLevel.NONE;
        }

        boolean male = gender.equalsIgnoreCase("M");
        boolean female = gender.equalsIgnoreCase("F");

        if (age > 30) {
            if (triggerCount >= 8) return RiskLevel.EARLY_ONSET;
            if (triggerCount >= 2 && triggerCount <= 5) return RiskLevel.BORDERLINE;
            return RiskLevel.BORDERLINE;
        }

        if (male) {
            if (triggerCount >= 5) return RiskLevel.EARLY_ONSET;
            if (triggerCount >= 3) return RiskLevel.IN_DANGER;
            return RiskLevel.NONE;
        }

        if (female) {
            if (triggerCount >= 7) return RiskLevel.EARLY_ONSET;
            if (triggerCount >= 6) return RiskLevel.IN_DANGER;
            return RiskLevel.NONE;
        }

        return RiskLevel.NONE;
    }

    public RiskResponse evaluateRisk(Long patientId) {
        Patient patient = patientClient.getPatient(patientId);
        List<Note> notes = notesClient.getNotes(patientId);

        int age = calculateAge(patient.getDateNaissance());
        int triggerCount = countTriggers(notes);
        RiskLevel level = calculateRisk(age, patient.getGenre(), triggerCount);

        return new RiskResponse(patientId, age, patient.getGenre(), triggerCount, level);
    }
}
