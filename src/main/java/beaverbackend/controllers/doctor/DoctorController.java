package beaverbackend.controllers.doctor;

import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.controllers.common.BadRequestRes;
import beaverbackend.controllers.common.VisitSearchReq;
import beaverbackend.controllers.common.VisitSearchRes;
import beaverbackend.enums.BadRequestDictEnum;
import beaverbackend.enums.ExaminationTypeEnum;
import beaverbackend.jpa.model.Visit;
import beaverbackend.jpa.repository.VisitRepository;
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
        try {
            return ResponseEntity.ok(doctorService.getVisitExaminationList(req));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }

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

    @PreAuthorize("hasAuthority('SCOPE_DOCTOR')")
    @PostMapping("/set-visit-status")
    public ResponseEntity<?> setVisitStatus(@RequestBody SetVisitStatusReq req) {
        try {
            return ResponseEntity.ok(visitService.setVisitStatus(req));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }
    }


}
