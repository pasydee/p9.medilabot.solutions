package medilabo.solutions.front.client;

import medilabo.solutions.front.config.FeignClientConfig;
import medilabo.solutions.front.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "patient-service",
        url = "${patient.service.url}",
        configuration = FeignClientConfig.class
)
public interface PatientClient {

    @PostMapping("/patients")
    Patient createPatient(@RequestBody Patient patient);

    @GetMapping("/patients/all")
    List<Patient> getAllPatients();

    @GetMapping("/patients/search")
    Patient searchPatient(
            @RequestParam String prenom,
            @RequestParam String nom,
            @RequestParam String dateNaissance
    );

    @PutMapping("/patients/{id}")
    void updatePatient(@PathVariable Long id, @RequestBody Patient patient);

    @GetMapping("/patients/{id}")
    Patient getPatientById(@PathVariable Long id);
}
