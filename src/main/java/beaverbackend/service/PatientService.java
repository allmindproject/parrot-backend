package beaverbackend.service;

import beaverbackend.controllers.receptionist.PatientCreateReq;
import beaverbackend.controllers.receptionist.PatientSearchReq;
import beaverbackend.jpa.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> searchPatients(PatientSearchReq req);

    Patient findByPatientInsuranceId(String insuranceNumber);

    Patient createPatient(PatientCreateReq req);

}
