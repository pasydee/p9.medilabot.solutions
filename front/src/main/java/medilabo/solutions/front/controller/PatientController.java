package medilabo.solutions.front.controller;

import medilabo.solutions.front.client.RiskClient;
import medilabo.solutions.front.model.Patient;
import medilabo.solutions.front.model.RiskResponse;
import medilabo.solutions.front.service.PatientOperationException;
import medilabo.solutions.front.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final RiskClient riskClient;

    public PatientController(PatientService patientService, RiskClient riskClient) {
        this.patientService = patientService;
        this.riskClient = riskClient;
    }

    @GetMapping
    public String showSearchForm() {
        return "patients";
    }

    @PostMapping("/create")
    public String createPatient(
            @RequestParam String prenom,
            @RequestParam String nom,
            @RequestParam String dateNaissance,
            @RequestParam String genre,
            @RequestParam(required = false) String adresse,
            @RequestParam(required = false) String telephone,
            Model model
    ) {
        try {
            Patient newPatient = new Patient();
            newPatient.setPrenom(prenom);
            newPatient.setNom(nom);
            newPatient.setDateNaissance(dateNaissance);
            newPatient.setGenre(genre);
            newPatient.setAdresse(adresse);
            newPatient.setTelephone(telephone);

            Patient saved = patientService.createPatient(newPatient);

            return "redirect:/patients/search?prenom=" + saved.getPrenom() +
                    "&nom=" + saved.getNom() +
                    "&dateNaissance=" + saved.getDateNaissance();

        } catch (PatientOperationException e) {
            model.addAttribute("error", e.getMessage());
            return "patients";
        }
    }

    @PostMapping("/search")
    public String searchPatient(
            @RequestParam String prenom,
            @RequestParam String nom,
            @RequestParam String dateNaissance,
            Model model
    ) {
        try {
            Patient patient = patientService.searchPatient(prenom, nom, dateNaissance);
            model.addAttribute("patient", patient);

            if (patient != null) {
                var notes = patientService.getNotes(patient.getId());
                model.addAttribute("notes", notes);

                RiskResponse risk = riskClient.getRisk(patient.getId());
                model.addAttribute("risk", risk);
            } else {
                model.addAttribute("error", "Aucun patient trouvé avec ces critères.");
            }

        } catch (PatientOperationException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "patients";
    }

    @GetMapping("/search")
    public String searchPatientGet(
            @RequestParam String prenom,
            @RequestParam String nom,
            @RequestParam String dateNaissance,
            Model model
    ) {
        try {
            Patient patient = patientService.searchPatient(prenom, nom, dateNaissance);
            model.addAttribute("patient", patient);

            if (patient != null) {
                var notes = patientService.getNotes(patient.getId());
                model.addAttribute("notes", notes);

                RiskResponse risk = riskClient.getRisk(patient.getId());
                model.addAttribute("risk", risk);
            } else {
                model.addAttribute("error", "Aucun patient trouvé avec ces critères.");
            }

        } catch (PatientOperationException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "patients";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(
            @PathVariable Long id,
            @RequestParam String prenom,
            @RequestParam String nom,
            @RequestParam String dateNaissance,
            @RequestParam String genre,
            @RequestParam String adresse,
            @RequestParam String telephone,
            Model model
    ) {
        Patient updated = new Patient();
        updated.setId(id);
        updated.setPrenom(prenom);
        updated.setNom(nom);
        updated.setDateNaissance(dateNaissance);
        updated.setGenre(genre);
        updated.setAdresse(adresse);
        updated.setTelephone(telephone);

        try {
            patientService.updatePatient(id, updated);

            Patient patient = patientService.getPatientById(id);
            model.addAttribute("patient", patient);

            if (patient != null) {
                var notes = patientService.getNotes(patient.getId());
                model.addAttribute("notes", notes);

                RiskResponse risk = riskClient.getRisk(patient.getId());
                model.addAttribute("risk", risk);
            }

        } catch (PatientOperationException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("patient", updated);

            Patient existing = patientService.getPatientById(id);
            if (existing != null) {
                var notes = patientService.getNotes(existing.getId());
                model.addAttribute("notes", notes);

                RiskResponse risk = riskClient.getRisk(existing.getId());
                model.addAttribute("risk", risk);
            }
        }

        return "patients";
    }

    @PostMapping("/add-note/{patientId}")
    public String addNote(
            @PathVariable Integer patientId,
            @RequestParam String content,
            Model model
    ) {
        patientService.addNote(Long.valueOf(patientId), content);

        Patient patient = patientService.getPatientById(Long.valueOf(patientId));
        model.addAttribute("patient", patient);

        if (patient != null) {
            var notes = patientService.getNotes(patient.getId());
            model.addAttribute("notes", notes);

            RiskResponse risk = riskClient.getRisk(patient.getId());
            model.addAttribute("risk", risk);
        }

        return "patients";
    }

    @PostMapping("/update-note/{noteId}")
    public String updateNote(
            @PathVariable String noteId,
            @RequestParam String content,
            @RequestParam Long patientId,
            Model model
    ) {
        try {
            patientService.updateNote(noteId, content);
        } catch (PatientOperationException e) {
            model.addAttribute("error", e.getMessage());
        }

        Patient patient = patientService.getPatientById(patientId);
        model.addAttribute("patient", patient);

        if (patient != null) {
            var notes = patientService.getNotes(patient.getId());
            model.addAttribute("notes", notes);

            RiskResponse risk = riskClient.getRisk(patient.getId());
            model.addAttribute("risk", risk);
        }

        return "patients";
    }

    @PostMapping("/delete-note/{noteId}")
    public String deleteNote(
            @PathVariable String noteId,
            @RequestParam Long patientId,
            Model model
    ) {
        try {
            patientService.deleteNote(noteId);
        } catch (PatientOperationException e) {
            model.addAttribute("error", e.getMessage());
        }

        Patient patient = patientService.getPatientById(patientId);
        model.addAttribute("patient", patient);

        if (patient != null) {
            var notes = patientService.getNotes(patient.getId());
            model.addAttribute("notes", notes);

            RiskResponse risk = riskClient.getRisk(patient.getId());
            model.addAttribute("risk", risk);
        }

        return "patients";
    }
}