package medilabo.solutions.risk_service.controller;

import medilabo.solutions.risk_service.model.RiskResponse;
import medilabo.solutions.risk_service.services.RiskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk")
public class RiskController {

    private final RiskService riskService;

    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }

    @GetMapping("/{patientId}")
    public RiskResponse getRisk(@PathVariable Long patientId) {
        return riskService.evaluateRisk(patientId);
    }
}

