package beaverbackend.controllers.supervisor;

import beaverbackend.controllers.common.LabExaminationSearchReq;
import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.jpa.model.LabExamination;
import beaverbackend.service.LabExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supervisor")
@RequiredArgsConstructor
public class SupervisorController {

    private final LabExaminationService labExaminationService;

    @PreAuthorize("hasAuthority('SCOPE_LAB_SUPER')")
    @PostMapping("/approve-examination")
    public ResponseEntity<LabExamination> labExamination(@RequestBody ApproveLabExaminationReq req) {
        return ResponseEntity.ok(labExaminationService.approveLabExamination(req));
    }

    @PreAuthorize("hasAuthority('SCOPE_LAB_SUPER')")
    @PostMapping("/reject-examination")
    public ResponseEntity<LabExamination> labExamination(@RequestBody RejectLabExaminationReq req) {
        return ResponseEntity.ok(labExaminationService.rejectLabExamination(req));
    }

    @PreAuthorize("hasAuthority('SCOPE_LAB_SUPER')")
    @GetMapping("/search-examination")
    public ResponseEntity<?> searchLabExamination(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "examinationCode", required = false) String examinationCode,
            @RequestParam(value = "labAssistantId", required = false) String labAssistantId,
            @RequestParam(value = "rightsLevel", required = false) String rightsLevel)
    {
        LabExaminationSearchReq req = new LabExaminationSearchReq(status, examinationCode, labAssistantId, rightsLevel);
        return ResponseEntity.ok(labExaminationService.supervisorSearchLabExamination(req));
    }

    @PreAuthorize("hasAuthority('SCOPE_LAB_SUPER')")
    @GetMapping("/find-examination")
    public ResponseEntity<?> findLabExamination() {
        return ResponseEntity.ok(labExaminationService.supervisorEasySearchLabExamination());
    }

}
