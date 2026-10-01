package medilabo.solutions.risk_service.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor bearerTokenRelayInterceptor() {
        return template -> {
            ServletRequestAttributes attrs =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs == null) return;

            HttpServletRequest request = attrs.getRequest();
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null) {
                template.header("Authorization", authorizationHeader);
            }
        };
    }
}