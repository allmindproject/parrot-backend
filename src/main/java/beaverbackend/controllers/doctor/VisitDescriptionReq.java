package beaverbackend.controllers.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class VisitDescriptionReq {

    @JsonProperty("visitId")
    private Long visitId;
    @JsonProperty("description")
    private String description;

}
