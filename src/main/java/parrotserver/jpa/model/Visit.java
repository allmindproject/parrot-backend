package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import parrotserver.enums.VisitStatusEnum;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "visit")
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "diagnostics")
    private String diagnostics;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VisitStatusEnum visitStatusEnum;

    @JoinColumn(name = "receptionist", referencedColumnName = "clinic_staff")
    @ManyToOne(fetch = FetchType.LAZY)
    private Receptionist receptionist;

    @JoinColumn(name = "selected_doctor", referencedColumnName = "clinic_staff")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor selectedDoctor;

    @JoinColumn(name = "patient", referencedColumnName = "insurance_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LabExamination> labExamination;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhysicalExamination> physicalExamination;

}
