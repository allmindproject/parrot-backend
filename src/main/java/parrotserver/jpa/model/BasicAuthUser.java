package parrotserver.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "basic_auth_user_info")
@NoArgsConstructor
@AllArgsConstructor
public class BasicAuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private long pid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user", referencedColumnName = "id")
    private AppUser user;

    @Column(name = "password")
    private String password;
}
