package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {

    @Column(name = "npwz_id")
    private String npwzId;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_staff", referencedColumnName = "clinic_emp_id")
    private ClinicStaff clinicStaff;

    @OneToMany(mappedBy = "selectedDoctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visit> visit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor that = (Doctor) o;
        return Objects.equals(getClinicStaff(), that.getClinicStaff());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClinicStaff());
    }
}
