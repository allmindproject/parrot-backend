package beaverbackend.controllers.doctor;

import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.controllers.common.BadRequestRes;
import beaverbackend.controllers.common.VisitSearchReq;
import beaverbackend.controllers.common.VisitSearchRes;
import beaverbackend.enums.BadRequestDictEnum;
import beaverbackend.enums.ExaminationTypeEnum;
import beaverbackend.jpa.model.Visit;
import beaverbackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final VisitService visitService;
    private final ExaminationDictionaryService examinationDictionaryService;

    @PreAuthorize("hasAuthority('SCOPE_DOCTOR')")
    @GetMapping("/search-visit")
    public ResponseEntity<List<VisitSearchRes>> searchVisit(@RequestBody VisitSearchReq req) {
        List<Visit> searchResult = visitService.searchVisits(req);
        List<VisitSearchRes> result = searchResult.stream()
                .map(visit -> new VisitSearchRes(visit, visit.getPatient(), visit.getSelectedDoctor())).toList();

        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('SCOPE_DOCTOR')")
    @GetMapping("/search-examination")
    public ResponseEntity<?> searchExamination(@RequestBody ExaminationDictSearchReq req) {
        return ResponseEntity.ok(examinationDictionaryService.searchExaminationDictionary(req));
    }

    @PreAuthorize("hasAuthority('SCOPE_DOCTOR')")
    @GetMapping("/search-visit-examination")
    public ResponseEntity<?> getExaminationForVisit(@RequestBody VisitExaminationListSearchReq req) {

        VisitExaminationSearchRes res = new VisitExaminationSearchRes();
        try {
            if (Objects.equals(req.getExaminationType(), "BOTH")) {
                res.setPhysicalExaminationList(doctorService.getVistPhysicalExaminationList(req.getVisitId()));
                res.setLabExaminationList(doctorService.getVistLabExaminationList(req.getVisitId()));
            } else if (ExaminationTypeEnum.valueOf(req.getExaminationType()) == ExaminationTypeEnum.PHYSICAL) {
                res.setPhysicalExaminationList(doctorService.getVistPhysicalExaminationList(req.getVisitId()));
            } else if (ExaminationTypeEnum.valueOf(req.getExaminationType()) == ExaminationTypeEnum.LABORATORY) {
                res.setLabExaminationList(doctorService.getVistLabExaminationList(req.getVisitId()));
            }
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(new BadRequestRes(BadRequestDictEnum.BAD_EXAMINATION_CODE, null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new BadRequestRes(BadRequestDictEnum.BAD_EXAMINATION_CODE, req.getExaminationType()));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }

        return ResponseEntity.ok(res);
    }

    @PreAuthorize("hasAuthority('SCOPE_DOCTOR')")
    @PostMapping("/add-physical-examination")
    public ResponseEntity<?> addPhysicalExamination(@RequestBody CreatePhysicalExaminationReq req) {
        try {
            return ResponseEntity.ok(doctorService.createPhysicalExamination(req));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_DOCTOR')")
    @PostMapping("/add-lab-examination")
    public ResponseEntity<?> addLabExamination(@RequestBody CreateLabExaminationReq req) {
        try {
            return ResponseEntity.ok(doctorService.createLabExamination(req));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }

    }

}
