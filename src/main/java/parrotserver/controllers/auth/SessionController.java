package parrotserver.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class SessionController {
    @GetMapping("/check-session")
    public ResponseEntity<Boolean> checkSession() throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
            if (details.getSessionId() != null) {
                return ResponseEntity.ok().body(true);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }
}
