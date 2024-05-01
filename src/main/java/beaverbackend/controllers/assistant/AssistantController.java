package beaverbackend.controllers.assistant;

import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.service.LabExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
public class AssistantController {

    private final LabExaminationService labExaminationService;

    @PreAuthorize("hasAuthority('SCOPE_LAB_ASSISTANT')")
    @PostMapping("/complete-examination")
    public ResponseEntity<?> labExamination(@RequestBody CompleteLabExaminationReq req) {
        try {
            return ResponseEntity.ok(labExaminationService.completeLabExamination(req));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_LAB_ASSISTANT')")
    @PostMapping("/cancel-examination")
    public ResponseEntity<?> labExamination(@RequestBody CancelLabExaminationReq req) {
        try {
            return ResponseEntity.ok(labExaminationService.cancelLabExamination(req));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getResponse());
        }
    }
}
