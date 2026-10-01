package medilabo.solutions.front.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Note {

    private String id;
    private Integer patientId;
    private String date;
    private String content;

    public String getDateAffichage() {
        if (date == null || date.isBlank()) {
            return "";
        }
        try {
            // On parse en supposant que la date reçue est en UTC
            LocalDateTime dateTimeUtc = LocalDateTime.parse(date);
            ZonedDateTime utcZoned = dateTimeUtc.atZone(ZoneId.of("UTC"));

            // Conversion vers le fuseau de Paris (gère automatiquement l'heure d'été/hiver)
            ZonedDateTime parisZoned = utcZoned.withZoneSameInstant(ZoneId.of("Europe/Paris"));

            return parisZoned.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (Exception e) {
            return date;
        }
    }
}