package sjs.instagram.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sjs.instagram.config.auth.PrincipalDetails;
import sjs.instagram.domain.Comment;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;
import sjs.instagram.form.PostForm;
import sjs.instagram.service.CommentService;
import sjs.instagram.service.PostService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/posts/new")
    public String createPost(PostForm postForm, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        postService.createPost(postForm, user.getId());
        //return "redirect:/user/userInfo";
        return "redirect:/" + user.getUserInfo().getInstagramId();
    }

    @GetMapping("/posts/new")
    public String postForm() {
        return "postForm";
    }

    @GetMapping("/posts/{postId}")
    public String postInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long postId, Model model) {
        User user = principalDetails.getUser();
        Post post = postService.findPost(postId);
        String instagramId = post.getUser().getUserInfo().getInstagramId();
        List<Comment> comments = post.getComments();

        if (post.getUser().getId() == user.getId()) model.addAttribute("isSame", true);
        else model.addAttribute("isSame", false);
        model.addAttribute("post", post);
        model.addAttribute("instagramId", instagramId);
        model.addAttribute("comments", comments);
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
