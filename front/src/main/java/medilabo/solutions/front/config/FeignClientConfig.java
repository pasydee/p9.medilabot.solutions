package medilabo.solutions.front.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig {

    private final JwtUtil jwtUtil;

    public FeignClientConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public RequestInterceptor bearerTokenInterceptor() {
        return template -> {
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                String token = jwtUtil.generateTokenForCurrentUser();
                template.header("Authorization", "Bearer " + token);
            }
        };
    }
}