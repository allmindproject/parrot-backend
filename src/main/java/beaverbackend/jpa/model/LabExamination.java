package beaverbackend.jpa.model;

import beaverbackend.enums.LaboratoryStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lab_examination")
public class LabExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "doctor_notices")
    private String doctorNotices;

    @Column(name = "supervisor_notices")
    private String supervisorNotices;

    @Column(name = "result")
    private String result;

    @NonNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LaboratoryStatusEnum status;

    @NonNull
    @Column(name = "ordered_date", nullable = false)
    private LocalDateTime orderedDate;

    @Column(name = "execution")
    private LocalDateTime executionDateTime; //cancel

    @Column(name = "approval")
    private LocalDateTime approvalDateTime; //reject

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examination_dictionary", referencedColumnName = "code", nullable = false)
    private ExaminationDictionary examinationDictionary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_assist", referencedColumnName = "lab_staff")
    private LabAssistant labAssistant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_super", referencedColumnName = "lab_staff")
    private LabSupervisor labSupervisor;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit", referencedColumnName = "id", nullable = false)
    private Visit visit;
}