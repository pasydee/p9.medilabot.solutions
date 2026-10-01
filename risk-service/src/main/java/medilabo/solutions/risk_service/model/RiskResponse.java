package medilabo.solutions.risk_service.model;

public record RiskResponse(
        Long patientId,
        int age,
        String gender,
        int triggerCount,
        RiskLevel riskLevel
) {}

