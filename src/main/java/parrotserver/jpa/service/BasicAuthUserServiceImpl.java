package parrotserver.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parrotserver.jpa.model.AppUser;
import parrotserver.jpa.model.BasicAuthUser;
import parrotserver.jpa.repository.BasicAuthUserRepository;

import java.util.Optional;

@Service
public class BasicAuthUserServiceImpl implements BasicAuthUserService {

    @Autowired
    BasicAuthUserRepository basicAuthUserRepository;

    @Override
    public Optional<BasicAuthUser> findPasswordByUser(AppUser user) {
        return basicAuthUserRepository.findByUser(user);
    }

    @Override
    public BasicAuthUser save(BasicAuthUser user) {
        return basicAuthUserRepository.save(user);
    }
}
