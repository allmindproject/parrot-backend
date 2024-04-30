package beaverbackend.service;

import beaverbackend.controllers.receptionist.SearchDoctorReq;
import beaverbackend.jpa.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> searchDoctors(SearchDoctorReq req);

    Doctor findByNpwzId(String npwzId);
}
