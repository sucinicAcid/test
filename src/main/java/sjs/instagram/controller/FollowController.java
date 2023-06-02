package sjs.instagram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sjs.instagram.config.auth.PrincipalDetails;
import sjs.instagram.domain.User;
import sjs.instagram.service.FollowService;
import sjs.instagram.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FollowController {

    private final UserService userService;
    private final FollowService followService;

    @GetMapping("/users/{instagramId}/followers")
    public String followers(@PathVariable String instagramId, Model model) {
        User user = userService.findUserByInstagramId(instagramId);
        List<User> followers = followService.findFollowers(user.getId());
        model.addAttribute("followers", followers);
        return "followers";
    }

    @GetMapping("/users/{instagramId}/followings")
    public String followings(@PathVariable String instagramId, Model model) {
        User user = userService.findUserByInstagramId(instagramId);
        List<User> followings = followService.findFollowings(user.getId());
        model.addAttribute("followings", followings);
        return "followings";
    }
}
