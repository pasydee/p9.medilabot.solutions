package medilabo.solutions.risk_service.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Patient {
    private Long id;
    private String prenom;
    private String nom;
    private LocalDate dateNaissance;
    private String genre;

}

