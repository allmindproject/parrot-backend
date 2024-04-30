package beaverbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import beaverbackend.controllers.receptionist.SearchDoctorReq;
import beaverbackend.jpa.model.Doctor;
import beaverbackend.jpa.repository.DoctorRepository;
import beaverbackend.jpa.specification.DoctorSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public List<Doctor> searchDoctors(SearchDoctorReq req) {
        return doctorRepository.findAll(DoctorSpecification.searchSpecification(req));
    }

    @Override
    public Doctor findByNpwzId(String npwzId) {
        return doctorRepository.findByNpwzId(npwzId);
    }
}
