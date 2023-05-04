package sjs.instagram.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sjs.instagram.config.auth.PrincipalDetails;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;
import sjs.instagram.domain.UserInfo;
import sjs.instagram.service.PostService;
import sjs.instagram.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;
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

    @GetMapping("/user/userInfo")
    public String userInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        User user = principalDetails.getUser();
        List<Post> posts = postService.findPosts(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "userInfo";
    }
}
