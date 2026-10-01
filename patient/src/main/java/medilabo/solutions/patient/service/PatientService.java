package medilabo.solutions.patient.service;

import medilabo.solutions.patient.model.Patient;
import medilabo.solutions.patient.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient non trouvé : " + id));

        existing.setPrenom(updated.getPrenom());
        existing.setNom(updated.getNom());
        existing.setDateNaissance(updated.getDateNaissance());
        existing.setGenre(updated.getGenre());
        existing.setAdresse(updated.getAdresse());
        existing.setTelephone(updated.getTelephone());

        return repository.save(existing);
    }

    public Patient searchPatient(String prenom, String nom, String dateNaissance) {

        LocalDate birthdate;
        try {
            birthdate = LocalDate.parse(dateNaissance);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Date de naissance invalide, format attendu AAAA-MM-JJ : " + dateNaissance);
        }

        Patient patient = repository.findByPrenomAndNomAndDateNaissance(
                prenom,
                nom,
                birthdate
        );

        if (patient == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Aucun patient trouvé avec ces critères");
        }

        return patient;
    }

}