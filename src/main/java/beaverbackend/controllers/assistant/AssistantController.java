package beaverbackend.controllers.assistant;

import beaverbackend.controllers.common.LabExaminationSearchReq;
import beaverbackend.jpa.model.LabExamination;
import beaverbackend.service.LabExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
public class AssistantController {

    private final LabExaminationService labExaminationService;

    @PreAuthorize("hasAuthority('SCOPE_LAB_ASSISTANT')")
    @PostMapping("/complete-examination")
    public ResponseEntity<LabExamination> completeLabExamination(@RequestBody CompleteLabExaminationReq req) {
        return ResponseEntity.ok(labExaminationService.completeLabExamination(req));
    }

    @PreAuthorize("hasAuthority('SCOPE_LAB_ASSISTANT')")
    @PostMapping("/cancel-examination")
    public ResponseEntity<LabExamination> cancelLabExamination(@RequestBody CancelLabExaminationReq req) {
        return ResponseEntity.ok(labExaminationService.cancelLabExamination(req));
    }

    @PreAuthorize("hasAuthority('SCOPE_LAB_ASSISTANT')")
    @GetMapping("/search-examination")
    public ResponseEntity<List<LabExamination>> searchLabExamination(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "examinationCode", required = false) String examinationCode,
            @RequestParam(value = "labAssistantId", required = false) String labAssistantId,
            @RequestParam(value = "rightsLevel", required = false) String rightsLevel,
            @RequestParam(value = "orderedDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderedDateTime)
    {
        LabExaminationSearchReq req = new LabExaminationSearchReq(status, examinationCode, labAssistantId, rightsLevel, orderedDateTime);
        return ResponseEntity.ok(labExaminationService.assistantSearchLabExamination(req));
    }

}
