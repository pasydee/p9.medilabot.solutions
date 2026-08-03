package medilabo.solutions.patient.controller;

import medilabo.solutions.patient.model.Patient;
import medilabo.solutions.patient.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    // ---------------------------
    // 1. Ajouter un patient
    // ---------------------------
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient saved = service.addPatient(patient);
        return ResponseEntity.ok(saved);
    }

    // ---------------------------
    // 2. Voir un patient
    // ---------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        return service.getPatient(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------------------------
    // 3. Mettre à jour un patient
    // ---------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        Patient updated = service.updatePatient(id, patient);
        return ResponseEntity.ok(updated);
    }
    // ---------------------------
    // 3. récupère tous les patients
    // ---------------------------
    @GetMapping("/all")
    public List<Patient> getPatient() {
        return service.getAllPatients();

    }
}
