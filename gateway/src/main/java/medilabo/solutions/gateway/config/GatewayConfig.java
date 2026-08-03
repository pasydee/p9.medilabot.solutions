package medilabo.solutions.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {

        /*return RouterFunctions.route()
                .GET("/patients/{*path}", request ->
                        ServerResponse.permanentRedirect(
                                java.net.URI.create("http://localhost:8081" + request.uri().getPath())
                        ).build()
                )
                .GET("/notes/{*path}", request ->
                        ServerResponse.permanentRedirect(
                                java.net.URI.create("http://localhost:8082" + request.uri().getPath())
                        ).build()
                )
                .GET("/risk/{*path}", request ->
                        ServerResponse.permanentRedirect(
                                java.net.URI.create("http://localhost:8083" + request.uri().getPath())
                        ).build()
                )

                .build();*/
        return route("patients").GET("/patients/**", http())
                .before(uri("https://localhost:8081"))
                .build();


    }
}
