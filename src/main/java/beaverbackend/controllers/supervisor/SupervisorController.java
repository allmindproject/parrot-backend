package beaverbackend.controllers.supervisor;

import beaverbackend.controllers.common.LabExaminationSearchReq;
import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.enums.BadRequestDictEnum;
import beaverbackend.jpa.model.LabExamination;
import beaverbackend.service.LabExaminationService;
import beaverbackend.utils.BeaverUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
            @RequestParam(value = "rightsLevel", required = false) String rightsLevel,
            @RequestParam(value = "orderedDateTime", required = false) String orderedDate)
    {
        LocalDateTime orderedDateTime;

        try {
            orderedDateTime = BeaverUtils.convertReqToDateTime(orderedDate);
        } catch (DateTimeException e) {
            throw new BadRequestException(BadRequestDictEnum.BAD_DATE, orderedDate);
        }

        LabExaminationSearchReq req = new LabExaminationSearchReq(status, examinationCode, labAssistantId, rightsLevel, orderedDateTime);
        return ResponseEntity.ok(labExaminationService.supervisorSearchLabExamination(req));
    }

    @PreAuthorize("hasAuthority('SCOPE_LAB_SUPER')")
    @GetMapping("/find-examination")
    public ResponseEntity<?> findLabExamination() {
        return ResponseEntity.ok(labExaminationService.supervisorEasySearchLabExamination());
    }

}
