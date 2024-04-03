package parrotserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import parrotserver.jpa.model.AppUser;
import parrotserver.jpa.service.AppUserService;
import parrotserver.jpa.service.BasicAuthUserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final BasicAuthUserService basicAuthUserService;
    private final AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String password = basicAuthUserService.findPasswordByUser(user).get().getPassword();
        User user1 = new User(username, password, Set.of(new SimpleGrantedAuthority(user.getRoleEnum().name())));
        return user1;
    }
}
