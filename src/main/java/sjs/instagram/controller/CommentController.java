package sjs.instagram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sjs.instagram.config.auth.PrincipalDetails;
import sjs.instagram.domain.User;
import sjs.instagram.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comment/new")
    public String comment(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long postId, String content) {
        User user = principalDetails.getUser();
        commentService.createComment(content, user.getId(), postId);
        return "redirect:/posts/" + postId;
    }
}
