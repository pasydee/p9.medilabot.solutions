package medilabo.solutions.risk_service.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Note {
    private String id;
    private Long patientId;
    private String content;
    private LocalDate date;

}

