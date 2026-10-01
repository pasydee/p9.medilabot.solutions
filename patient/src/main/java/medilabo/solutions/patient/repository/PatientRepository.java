package medilabo.solutions.patient.repository;

import medilabo.solutions.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByPrenomAndNomAndDateNaissance(String prenom, String nom, LocalDate dateNaissance);
}
