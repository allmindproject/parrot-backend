package parrotserver.jpa.service;

import parrotserver.jpa.model.AppUser;
import parrotserver.jpa.model.BasicAuthUser;

import java.util.Optional;

public interface BasicAuthUserService {

    public Optional<BasicAuthUser> findPasswordByUser(AppUser user);

    public BasicAuthUser save(BasicAuthUser user);

}
