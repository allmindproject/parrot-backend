package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @Column(name = "insurance_id")
    private String insuranceId;

    @OneToOne
    @JoinColumn(name = "person", referencedColumnName = "nationalIDNumber")
    private Person person;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visit> visit;
}
