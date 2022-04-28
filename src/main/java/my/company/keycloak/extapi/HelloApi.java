package my.company.keycloak.extapi;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class HelloApi {
    private final WebClient webClient;

    public HelloApi(WebClient webClient) {
        this.webClient = webClient;

    }
    public String getHello(KeycloakAuthenticationToken principal) {
        return webClient.get()
                .uri("http://localhost:8091/api/hello")
                .header("Authorization", "Bearer " + principal.getAccount().getKeycloakSecurityContext().getTokenString())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
