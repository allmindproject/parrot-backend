package beaverbackend.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class CredentialsEncoder {

    private static final Logger logger = LoggerFactory.getLogger(CredentialsEncoder.class);

    private final String doctorUser = "doctor1@email.com:doctor1";
    private final String receptionistUser = "receptionist1@email.com:receptionist1";

    public String base64Encode(String toEncode) {
        return new String(Base64.getEncoder().encode(toEncode.getBytes(StandardCharsets.UTF_8)));
    }

    @Bean
    CommandLineRunner logUserCredentials() {
        return args -> logger.info("Encoded login data: {}", base64Encode(doctorUser));
    }

}
