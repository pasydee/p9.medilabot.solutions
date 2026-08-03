package medilabo.solutions.front.service;

import medilabo.solutions.front.model.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Patient> getAllPatients() {

        Patient[] patients = restTemplate.getForObject(
                "http://localhost:8080/patients/all",
                Patient[].class
        );

        if (patients == null) {
            return List.of(); // évite le crash
        }

        return Arrays.asList(patients);
    }

}
