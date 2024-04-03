package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "lab_staff")
@AllArgsConstructor
@NoArgsConstructor
public class LabStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lab_emp_id")
    private long labEmpId;

    @OneToOne
    @JoinColumn(name = "person", referencedColumnName = "nationalIDNumber")
    private Person person;

    @OneToOne(mappedBy = "labStaff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LabSupervisor supervisor;

    @OneToOne(mappedBy = "labStaff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LabAssistant assistant;

}
