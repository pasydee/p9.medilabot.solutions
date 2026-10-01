package medilabo.solutions.front.config;

import medilabo.solutions.front.model.AppUser;
import medilabo.solutions.front.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminAccountInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAccountInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@medilabo.solutions").isEmpty()) {
            AppUser admin = new AppUser();
            admin.setEmail("admin@medilabo.solutions");
            admin.setPassword(passwordEncoder.encode("ChangeMe123!"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }
}