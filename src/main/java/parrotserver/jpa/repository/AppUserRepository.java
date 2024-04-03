package parrotserver.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import parrotserver.jpa.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public Optional<AppUser> findByEmail(String name);

}
