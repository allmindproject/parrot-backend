package beaverbackend.service;

import beaverbackend.controllers.receptionist.SearchPatientReq;
import beaverbackend.jpa.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> searchPatients(SearchPatientReq req);

    Patient findByPatientInsuranceId(String insuranceNumber);

}
