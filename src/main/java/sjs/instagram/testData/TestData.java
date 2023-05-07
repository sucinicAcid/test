package sjs.instagram.testData;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sjs.instagram.domain.User;
import sjs.instagram.domain.UserInfo;
import sjs.instagram.service.UserService;

@Component
@RequiredArgsConstructor
public class TestData {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void makeUser() {
        User user = new User(new UserInfo("name", "photo", "instagram_id", "introduction"));

        user.changeRole("ROLE_USER");
        user.setUsername("123");
        user.setPassword("123");
        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userService.joinUser(user);
    }
}
