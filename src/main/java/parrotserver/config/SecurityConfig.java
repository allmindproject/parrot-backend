package parrotserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import parrotserver.config.auth.AuthFailureHandler;
import parrotserver.config.auth.BasicAuthLoginSuccessHandler;
import parrotserver.config.auth.OAuth2LoginSuccessHandler;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

    @Value("&{frontend.url}")
    private String frontendUrl;

    @Autowired
    OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    @Autowired
    BasicAuthLoginSuccessHandler basicAuthLoginSuccessHandler;
    @Autowired
    AuthFailureHandler failureHandler;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173/", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login").permitAll();
                    auth.requestMatchers("/api/check-session").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(auth -> {
                    auth.successHandler(oAuth2LoginSuccessHandler);
                    auth.failureHandler(failureHandler);
                })
                .formLogin(auth -> {
                    auth.successHandler(basicAuthLoginSuccessHandler);
                    auth.failureHandler(failureHandler);
                });
        return http.build();
    }

}
