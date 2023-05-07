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

    private final PostService postService;

    @GetMapping("/user/userInfo")
    public String userInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        User user = principalDetails.getUser();
        List<Post> posts = postService.findPosts(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "userInfo";
    }
}
