package parrotserver.jpa.service;

import org.springframework.stereotype.Service;
import parrotserver.jpa.model.AppUser;

import java.util.Optional;

@Service
public interface AppUserService {

    public AppUser save(AppUser user);

    public Optional<AppUser> findByEmail(String email);

}
