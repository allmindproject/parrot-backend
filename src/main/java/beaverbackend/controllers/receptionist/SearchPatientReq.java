package beaverbackend.controllers.receptionist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SearchPatientReq {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("nationalIdNumber")
    private String nationalIdNumber;

    @JsonProperty("insuranceId")
    private String insuranceId;

}
