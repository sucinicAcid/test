package sjs.instagram.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final UserService userService;

    /*
    @GetMapping("/user/userInfo")
    public String userInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        User user = principalDetails.getUser();
        List<Post> posts = postService.findPosts(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "userInfo";
    }

    @GetMapping("/user/{instagram_id}")
    public String userInfo2(@PathVariable String instagram_id, Model model) {
        User user = userService.findUserByInstagram_id(instagram_id);
        model.addAttribute("user", user);
        return "userElseInfo";
    }
    */

    @GetMapping("/{instagramId}")
    public String userInfo(@PathVariable String instagramId, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        // 존재하는 사용자인지 판별
        User findUser = userService.findUserByInstagramId(instagramId);
        if (findUser == null) {
            //TODO
            //throw new NoUserFoundException();
        }

        model.addAttribute("userInfo", findUser.getUserInfo());
        // user.getPost() 안됨. user가 영속성 컨텍스트에 존재하지 않기 때문에 user.getPost() 안됨
        List<Post> posts = postService.findPosts(findUser.getId());
        model.addAttribute("posts", posts);

        User user = principalDetails.getUser();
        if (user.getUserInfo().getInstagramId().equals(instagramId)) return "userInfo";
        else return "userElseInfo";
    }
}
