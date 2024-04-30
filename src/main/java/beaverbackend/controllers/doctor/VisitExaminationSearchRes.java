package beaverbackend.controllers.doctor;

import beaverbackend.jpa.model.LabExamination;
import beaverbackend.jpa.model.PhysicalExamination;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VisitExaminationSearchRes {

    @JsonProperty("physicalExaminationList")
    private List<PhysicalExamination> physicalExaminationList;

    @JsonProperty("labExaminationList")
    private List<LabExamination> labExaminationList;
}
