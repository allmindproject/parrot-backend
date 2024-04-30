package beaverbackend.service.auth;

import beaverbackend.controllers.auth.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {

    public AuthResponse getJwtTokensAfterAuthentication(Authentication authentication, HttpServletResponse response);

    public Object getAccessTokenUsingRefreshToken(String authorizationHeader);

}
