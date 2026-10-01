package medilabo.solutions.front.controller;

import medilabo.solutions.front.model.AppUser;
import medilabo.solutions.front.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/new")
    public String showCreateForm() {
        return "admin/create-user";
    }

    @PostMapping
    public String createUser(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String role,
                             Model model) {

        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Un compte existe déjà avec cet email.");
            return "admin/create-user";
        }

        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);

        return "redirect:/admin/users/new?success";
    }
}