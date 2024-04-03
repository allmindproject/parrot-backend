package parrotserver.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parrotserver.jpa.model.AppUser;
import parrotserver.jpa.repository.AppUserRepository;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public AppUser save(AppUser user) {
        return appUserRepository.save(user);
    }


    @Override
    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }
}
