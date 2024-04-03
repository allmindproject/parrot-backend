package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import parrotserver.enums.SexEnum;

@Getter
@Setter
@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @Column(nullable = false, unique = true)
    @OrderBy("nationalIDNumber ASC")
    private String nationalIDNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user", referencedColumnName = "id")
    private AppUser user;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Patient patient;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LabStaff labStaff;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ClinicStaff clinicStaff;


}
