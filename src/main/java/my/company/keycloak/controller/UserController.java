package my.company.keycloak.controller;

import org.keycloak.TokenVerifier;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/current")
    public String getCurrentUser(KeycloakAuthenticationToken principal) throws VerificationException {
        String userId = principal.getAccount().getPrincipal().getName();//так можно достать id из токена

        String token = principal.getAccount().getKeycloakSecurityContext().getTokenString();

        TokenVerifier<AccessToken> accessTokenTokenVerifier = TokenVerifier.create(token, AccessToken.class);
        String userName = accessTokenTokenVerifier.getToken().getPreferredUsername();//так можно достать имя и кучу всего из токена
        return "UserName = " + userName + "\nUserId = " + userId;
    }
}
