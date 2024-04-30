package beaverbackend.controllers.receptionist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SearchDoctorReq {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("nationalIdNumber")
    private String nationalIdNumber;

    @JsonProperty("npwzId")
    private String npwzId;
}
