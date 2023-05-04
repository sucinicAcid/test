package sjs.instagram.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sjs.instagram.config.auth.PrincipalDetails;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;
import sjs.instagram.form.PostForm;
import sjs.instagram.service.PostService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public String createPost(PostForm postForm, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        postService.createPost(postForm, user.getId());
        return "redirect:/user/userInfo";
    }

    @GetMapping("/posts/new")
    public String postForm() {
        return "postForm";
    }

    @GetMapping("/posts/{postId}")
    public String postInfo(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "postInfo";
    }

    @GetMapping("/posts/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "postEditForm";
    }
    @PostMapping("/posts/{postId}/edit")
    public String editPost(PostForm postForm, @PathVariable Long postId) {
        //TODO 작성자만 수정할 수 있게 검증
        Post post = postService.updatePost(postForm, postId);
        return "redirect:/posts/" + postId;
    }
}
