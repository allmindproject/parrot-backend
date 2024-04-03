package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "receptionist")
public class Receptionist {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_staff", referencedColumnName = "clinic_emp_id")
    private ClinicStaff clinicStaff;

    @OneToMany(mappedBy = "receptionist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visit> visit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receptionist that = (Receptionist) o;
        return Objects.equals(getClinicStaff(), that.getClinicStaff());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClinicStaff());
    }
}