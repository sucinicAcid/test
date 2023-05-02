package sjs.instagram.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sjs.instagram.domain.User;
import sjs.instagram.domain.UserInfo;
import sjs.instagram.service.UserService;

@Controller
@AllArgsConstructor
public class IndexController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @ResponseBody
    @GetMapping("/")
    String index() {
        return "메인 페이지";
    }

    @GetMapping("/loginForm")
    String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    String joinForm() {
        return "joinForm";
    }

    // instagram_id를 User.userInfo안에 바인딩 되는법 찾기. 지금은 임시로
    @PostMapping("/joinProc")
    String joinProc(User user, String instagram_id) {
        // ROLE_ 추가해야함
        user.changeRole("ROLE_USER");
        user.changeUserInfo(new UserInfo("name", "photo", instagram_id, "introduction"));
        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userService.joinUser(user);
        return "redirect:/loginForm";
    }
}
