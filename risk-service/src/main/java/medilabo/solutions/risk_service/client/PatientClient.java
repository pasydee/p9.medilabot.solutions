package medilabo.solutions.risk_service.client;

import medilabo.solutions.risk_service.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "patient-service",
        url = "${PATIENT_SERVICE_URL}")
public interface PatientClient {

    @GetMapping("/patients/{id}")
    Patient getPatient(@PathVariable Long id);
}

