package my.company.keycloak.controller;

import my.company.keycloak.extapi.HelloApi;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ext")
public class ExternalController {
    private final HelloApi helloApi;

    public ExternalController(HelloApi helloApi) {
        this.helloApi = helloApi;
    }

    @GetMapping
    public String getExternal(KeycloakAuthenticationToken principal) {
        return helloApi.getHello(principal);
    }
}
