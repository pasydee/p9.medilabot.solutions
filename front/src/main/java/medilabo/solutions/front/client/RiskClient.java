package medilabo.solutions.front.client;

import medilabo.solutions.front.config.FeignClientConfig;
import medilabo.solutions.front.model.RiskResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "risk-service",
        url = "${risk.service.url}",
        configuration = FeignClientConfig.class
)
public interface RiskClient {

    @GetMapping("/risk/{patientId}")
    RiskResponse getRisk(@PathVariable Long patientId);
}