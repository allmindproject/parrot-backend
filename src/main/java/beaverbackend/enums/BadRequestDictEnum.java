package beaverbackend.enums;

import lombok.Getter;

@Getter
public enum BadRequestDictEnum {

    //Login
    BAD_LOGIN("Bad credentials"),
    BAD_OAUTH2_LOGIN("Bad oAuth2 Login"),

    //JWT
    EXPIRED_JWT("JWT has expired"),

    //User
    BAD_USER("Could not find user in DB"),
    BAD_RECEPTIONIST_ID("Could not find receptionist - Internal backend problem, contact author"),
    BAD_DOCTOR_NPWZ_ID("Could not find doctor with specified npwz ID"),
    BAD_PATIENT_INSURANCE_ID("Could not find patient with specified insurance ID"),

    //Parse
    BAD_DATE("Could not parse date"),

    //Visit
    BAD_VISIT_ID("Could not find visit by id"),
    BAD_VISIT_STATUS("Could not accept provided visit status"),

    //Examination
    BAD_EXAMINATION_CODE("Could not accept provided examination code"),
    BAD_P_EXAMINATION_RESULT("Could not accept provided physical examination result"),
    BAD_LAB_EXAMINATION_DOCTOR_NOTICE("Could not accept provided doctor notice"),
    BAD_EXAMINATION_TYPE("Could not accept provided examination type")

    ;

    private final String description;

    private BadRequestDictEnum(String description) {
        this.description = description;
    }

}
