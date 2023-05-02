package sjs.instagram.config.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sjs.instagram.domain.User;
import sjs.instagram.repository.UserRepository;

@Service
@AllArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new PrincipalDetails(user);
        }
        // TODO
        // 나중에 해결
        return null;
    }
}
