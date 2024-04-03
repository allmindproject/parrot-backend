package parrotserver.config.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import parrotserver.enums.RoleEnum;
import parrotserver.jpa.model.AppUser;
import parrotserver.jpa.service.AppUserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${frontend.url}")
    private String frontendUrl;

    private final AppUserService appUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;

        if ("github".equals(authToken.getAuthorizedClientRegistrationId())) {
            DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();

            String email = attributes.getOrDefault("email", "").toString();

            appUserService.findByEmail(email).ifPresentOrElse(user -> {
                DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(user.getRoleEnum().name())),
                        attributes, "id");
                Authentication securityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority(user.getRoleEnum().name())),
                        authToken.getAuthorizedClientRegistrationId());
                SecurityContextHolder.getContext().setAuthentication(securityAuth);
            }, () -> {
                AppUser appUser = AppUser.builder()
                        .email(email)
                        .roleEnum(RoleEnum.USER)
                        .build();
                appUserService.save(appUser);
                DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(appUser.getRoleEnum().name())),
                        attributes, "id");
                Authentication securityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority(appUser.getRoleEnum().name())),
                        authToken.getAuthorizedClientRegistrationId());
                SecurityContextHolder.getContext().setAuthentication(securityAuth);
            });


        }

        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(frontendUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
