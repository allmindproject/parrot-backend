package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "clinic_staff")
@AllArgsConstructor
@NoArgsConstructor
public class ClinicStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_emp_id")
    private Long clinicEmpId;

    @OneToOne
    @JoinColumn(name = "person", referencedColumnName = "nationalIDNumber")
    private Person person;

    @OneToOne(mappedBy = "clinicStaff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Doctor doctor;

    @OneToOne(mappedBy = "clinicStaff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Receptionist receptionist;

}
