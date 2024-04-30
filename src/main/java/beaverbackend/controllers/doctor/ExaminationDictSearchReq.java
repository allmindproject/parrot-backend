package beaverbackend.controllers.doctor;

import beaverbackend.enums.ExaminationTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ExaminationDictSearchReq {
    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
}
