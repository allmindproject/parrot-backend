package beaverbackend.service;

import beaverbackend.config.db.DbRegisterService;
import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.controllers.receptionist.PatientCreateReq;
import beaverbackend.controllers.receptionist.PatientSearchReq;
import beaverbackend.enums.BadRequestDictEnum;
import beaverbackend.jpa.model.Patient;
import beaverbackend.jpa.repository.PatientRepository;
import beaverbackend.jpa.specification.PatientSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DbRegisterService dbRegisterService;

    @Override
    public List<Patient> searchPatients(PatientSearchReq req) {
        return patientRepository.findAll(PatientSpecification.searchSpecification(req));
    }

    @Override
    public Patient findByPatientInsuranceId(String insuranceId) {
        return patientRepository.findByInsuranceId(insuranceId).orElse(null);
    }

    @Override
    public Patient createPatient(PatientCreateReq req) {
        if (req.getEmail() == null)
            throw new BadRequestException(BadRequestDictEnum.NO_EMAIL, null);
        if (req.getNationalIdNumber() == null)
            throw new BadRequestException(BadRequestDictEnum.NO_NATIONAL_ID, null);
        if (req.getFirstName() == null)
            throw new BadRequestException(BadRequestDictEnum.NO_FIRST_NAME, null);
        if (req.getLastName() == null)
            throw new BadRequestException(BadRequestDictEnum.NO_LAST_NAME, null);
        if (req.getSex() == null)
            throw new BadRequestException(BadRequestDictEnum.NO_SEX, null);
        if (req.getInsuranceId() == null)
            throw new BadRequestException(BadRequestDictEnum.NO_INSURANCE_ID, null);

        return dbRegisterService.createPatient(req.getEmail(), req.getNationalIdNumber(), req.getFirstName(), req.getLastName(), req.getSex(), "ABCDEFGHIJKLMNOPQ", req.getInsuranceId());

    }
}
