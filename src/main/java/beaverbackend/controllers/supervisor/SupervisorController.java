package beaverbackend.controllers.supervisor;

import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.jpa.model.LabExamination;
import beaverbackend.service.LabExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supervisor")
@RequiredArgsConstructor
public class SupervisorController {

    private final LabExaminationService labExaminationService;

    @PreAuthorize("hasAuthority('SCOPE_LAB_SUPER')")
    @PostMapping("/approve-examination")
    public ResponseEntity<?> labExamination(@RequestBody ApproveLabExaminationReq req) {
        try {
            LabExamination examination =  labExaminationService.approveLabExamination(req);
            return ResponseEntity.ok(examination);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_LAB_SUPER')")
    @PostMapping("/reject-examination")
    public ResponseEntity<?> labExamination(@RequestBody RejectLabExaminationReq req) {
        try {
            return ResponseEntity.ok(labExaminationService.rejectLabExamination(req));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }
    }

}
