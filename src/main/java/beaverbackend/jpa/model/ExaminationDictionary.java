package beaverbackend.jpa.model;

import beaverbackend.enums.ExaminationTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "examination_dictionary")
public class ExaminationDictionary {

    @Id
    @NonNull
    @Column(name = "code")
    private String code;

    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    @NonNull
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExaminationTypeEnum type;

    @OneToMany(mappedBy = "examinationDictionary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PhysicalExamination> physicalExaminationList;

    @OneToMany(mappedBy = "examinationDictionary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LabExamination> labExaminationList;

    public ExaminationDictionary(@NonNull String code, @NonNull String description, @NonNull ExaminationTypeEnum type) {
        this.code = code;
        this.description = description;
        this.type = type;
    }

}
