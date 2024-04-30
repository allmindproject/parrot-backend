package beaverbackend.enums;

import lombok.Getter;

@Getter
public enum BadRequestDictEnum {

    //Login
    BAD_LOGIN("Bad credentials"),
    BAD_OAUTH2_LOGIN("Bad oAuth2 Login"),

    //User
    BAD_RECEPTIONIST_ID("Could not find receptionist - Internal backend problem, contact author"),
    BAD_DOCTOR_NPWZ_ID("Could not find doctor with specified npwz ID"),
    BAD_PATIENT_INSURANCE_ID("Could not find patient with specified insurance ID"),

    //Parse
    BAD_DATE("Could not parse date"),

    //Visit
    BAD_VISIT_ID("Could not find visit by id"),

    //Examination
    BAD_EXAMINATION_CODE("Incorrect examination type"),

    ;

    private final String description;

    private BadRequestDictEnum(String description) {
        this.description = description;
    }

}
