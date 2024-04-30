package beaverbackend.jpa.model;

import beaverbackend.enums.RightsLevelEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lab_supervisor")
public class LabSupervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "rightsLevel", nullable = false)
    @Enumerated(EnumType.STRING)
    private RightsLevelEnum rightsLevel;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_staff", referencedColumnName = "lab_emp_id", nullable = false)
    private LabStaff labStaff;

    @OneToMany(mappedBy = "labSupervisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LabExamination> labExaminationList;

    public LabSupervisor(@NonNull LabStaff labStaff, @NonNull RightsLevelEnum rightsLevel) {
        this.labStaff = labStaff;
        this.rightsLevel = rightsLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabSupervisor that = (LabSupervisor) o;
        return Objects.equals(getLabStaff(), that.getLabStaff());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabStaff());
    }
}
