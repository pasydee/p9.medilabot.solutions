package medilabo.solutions.front.model;

import lombok.Data;

@Data
public class RiskResponse {
    private Long patientId;
    private int age;
    private String genre;
    private int triggerCount;
    private String riskLevel;
}
