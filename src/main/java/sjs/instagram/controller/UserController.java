package sjs.instagram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sjs.instagram.config.auth.PrincipalDetails;
import sjs.instagram.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/userInfo")
    public String userInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        model.addAttribute("user", principalDetails.getUser());
        return "userInfo";
    }
}
