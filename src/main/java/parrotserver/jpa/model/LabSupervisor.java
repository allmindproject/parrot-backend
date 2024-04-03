package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import parrotserver.enums.RightsLevelEnum;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lab_supervisor")
public class LabSupervisor {
    @Enumerated(EnumType.STRING)
    private RightsLevelEnum rightsLevel;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_staff", referencedColumnName = "lab_emp_id")
    private LabStaff labStaff;

    @OneToMany(mappedBy = "labSupervisor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LabExamination> labExamination;

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
