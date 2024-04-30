package beaverbackend.service;

import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.controllers.doctor.CreateLabExaminationReq;
import beaverbackend.controllers.doctor.CreatePhysicalExaminationReq;
import beaverbackend.enums.BadRequestDictEnum;
import beaverbackend.enums.ExaminationTypeEnum;
import beaverbackend.jpa.model.*;
import beaverbackend.jpa.repository.*;
import beaverbackend.utils.BeaverUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import beaverbackend.controllers.receptionist.DoctorSearchReq;
import beaverbackend.jpa.specification.DoctorSpecification;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ExaminationDictionaryRepository examinationDictionaryRepository;
    private final VisitRepository visitRepository;
    private final PhysicalExaminationRepository physicalExaminationRepository;
    private final LabExaminationRepository labExaminationRepository;

    @Override
    public List<Doctor> searchDoctors(DoctorSearchReq req) {
        return doctorRepository.findAll(DoctorSpecification.searchSpecification(req));
    }

    @Override
    public Doctor findByNpwzId(String npwzId) {
        return doctorRepository.findByNpwzId(npwzId).orElse(null);
    }

    @Override
    public PhysicalExamination createPhysicalExamination(CreatePhysicalExaminationReq req) throws BadRequestException {
        Visit visit = visitRepository.findById(req.getVisitId())
                .orElseThrow(() -> new BadRequestException(BadRequestDictEnum.BAD_VISIT_ID, req.getVisitId() != null ? req.getVisitId().toString() : null));
        ExaminationDictionary examinationDict = examinationDictionaryRepository
                .findByCode(req.getExaminationDictCode())
                .orElseThrow(() -> new BadRequestException(BadRequestDictEnum.BAD_EXAMINATION_CODE, req.getExaminationDictCode() != null ? req.getExaminationDictCode() : null));

        LocalDateTime examinationDateTime;
        try {
            examinationDateTime = BeaverUtils.convertReqToDateTime(req.getExaminationDateTime());
        } catch (DateTimeParseException e) {
            throw new BadRequestException(BadRequestDictEnum.BAD_DATE, req.getExaminationDateTime());
        }

        PhysicalExamination newExamination = new PhysicalExamination(req.getResult(), examinationDict, examinationDateTime, visit);
        return physicalExaminationRepository.save(newExamination);
    }

    @Override
    public List<PhysicalExamination> getVistPhysicalExaminationList(Long visitId) throws BadRequestException {
        return visitRepository.findById(visitId)
                .orElseThrow(() -> new BadRequestException(BadRequestDictEnum.BAD_VISIT_ID, visitId != null ? visitId.toString() : null))
                .getPhysicalExaminationList();
    }

    @Override
    public LabExamination createLabExamination(CreateLabExaminationReq req) throws BadRequestException {

        Visit visit = visitRepository.findById(req.getVisitId())
                .orElseThrow(() -> new BadRequestException(BadRequestDictEnum.BAD_VISIT_ID, req.getVisitId() != null ? req.getVisitId().toString() : null));
        ExaminationDictionary examinationDictionary = examinationDictionaryRepository.findByCode(req.getExaminationCode())
                .orElseThrow(() -> new BadRequestException(BadRequestDictEnum.BAD_EXAMINATION_CODE, req.getExaminationCode() != null ? req.getExaminationCode() : null));

        if (examinationDictionary.getType() != ExaminationTypeEnum.LABORATORY)
            throw new BadRequestException(BadRequestDictEnum.BAD_EXAMINATION_CODE, examinationDictionary.getType().toString());

        LabExamination labExamination = new LabExamination(req.getDoctorNotices(), examinationDictionary, visit);

        return labExaminationRepository.save(labExamination);
    }

    @Override
    public List<LabExamination> getVistLabExaminationList(Long visitId) throws BadRequestException {
        return visitRepository.findById(visitId).orElseThrow(() -> new BadRequestException(BadRequestDictEnum.BAD_VISIT_ID, visitId != null ? visitId.toString() : null)).getLabExaminationList();
    }
}
