package parrotserver.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import parrotserver.jpa.model.AppUser;
import parrotserver.jpa.model.BasicAuthUser;

import java.util.Optional;

@Repository
public interface BasicAuthUserRepository extends JpaRepository<BasicAuthUser, Long> {
    public Optional<BasicAuthUser> findByUser(AppUser user);

}
