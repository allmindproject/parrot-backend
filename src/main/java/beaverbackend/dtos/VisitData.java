package beaverbackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import beaverbackend.jpa.model.Person;

public class VisitData {

    @JsonProperty("patient")
    private Person assignedPatient;

    @JsonProperty("doctor")
    private Person assignedDoctor;

}
