package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "examination_dictionary")
public class ExaminationDictionary {

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "examinationDictionary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhysicalExamination> physicalExamination;

    @OneToMany(mappedBy = "examinationDictionary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LabExamination> labExamination;

}
