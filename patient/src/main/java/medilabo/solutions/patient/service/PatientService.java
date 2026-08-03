package medilabo.solutions.patient.service;

import medilabo.solutions.patient.model.Patient;
import medilabo.solutions.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient addPatient(Patient patient) {
        return repository.save(patient);
    }

    public Optional<Patient> getPatient(Long id) {
        return repository.findById(id);
    }

    public List<Patient> getAllPatients(){
        return repository.findAll();
    }

    public Patient updatePatient(Long id, Patient updated) {
        Patient existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé : " + id));

        existing.setPrenom(updated.getPrenom());
        existing.setNom(updated.getNom());
        existing.setDateNaissance(updated.getDateNaissance());
        existing.setGenre(updated.getGenre());
        existing.setAdresse(updated.getAdresse());
        existing.setTelephone(updated.getTelephone());

        return repository.save(existing);
    }


}
