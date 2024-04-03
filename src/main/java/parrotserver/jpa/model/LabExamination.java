package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import parrotserver.enums.LaboratoryStatusEnum;

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
    private long id;
    @Column(name = "result")
    private String result;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LaboratoryStatusEnum status;
    @Column(name = "notices")
    private String notices;
    @Column(name = "approval")
    private LocalDateTime approvalDateTime;
    @Column(name = "execution")
    private LocalDateTime executionDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examination_dictionary", referencedColumnName = "code")
    private ExaminationDictionary examinationDictionary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_assist", referencedColumnName = "lab_staff")
    private LabAssistant labAssistant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_super", referencedColumnName = "lab_staff")
    private LabSupervisor labSupervisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit", referencedColumnName = "id")
    private Visit visit;
}
