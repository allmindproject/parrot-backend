package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "physical_examination")
public class PhysicalExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "result")
    private String result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examination_dictionary", referencedColumnName = "code")
    private ExaminationDictionary examinationDictionary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit", referencedColumnName = "id")
    private Visit visit;
}
