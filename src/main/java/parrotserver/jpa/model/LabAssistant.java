package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class LabAssistant {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_staff", referencedColumnName = "lab_emp_id")
    private LabStaff labStaff;

    @OneToMany(mappedBy = "labAssistant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LabExamination> labExamination;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabAssistant that = (LabAssistant) o;
        return Objects.equals(getLabStaff(), that.getLabStaff());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabStaff());
    }
}
