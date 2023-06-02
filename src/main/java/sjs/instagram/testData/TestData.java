package sjs.instagram.testData;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sjs.instagram.domain.*;
import sjs.instagram.form.PostForm;
import sjs.instagram.repository.FollowRepository;
import sjs.instagram.service.CommentService;
import sjs.instagram.service.FollowService;
import sjs.instagram.service.PostService;
import sjs.instagram.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TestData {

    private final UserService userService;
    private final FollowService followService;
    private final PostService postService;
    private final CommentService commentService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void makeUsers() {
        // 사용자 생성
        User user1 = new User(new UserInfo("석진성", "양주사진", "zinsooon", "내가 알아서 할게"));
        user1.changeRole("ROLE_USER");
        user1.setUsername("123");
        user1.setPassword("123");
        String rawPassword1 = user1.getPassword();
        String encPassword1 = passwordEncoder.encode(rawPassword1);
        user1.setPassword(encPassword1);
        userService.joinUser(user1);

        User user2 = new User(new UserInfo("조민경", "소주사진", "min_owzl", "어쩔티비"));
        user2.changeRole("ROLE_USER");
        user2.setUsername("234");
        user2.setPassword("234");
        String rawPassword2 = user2.getPassword();
        String encPassword2 = passwordEncoder.encode(rawPassword2);
        user2.setPassword(encPassword2);
        userService.joinUser(user2);


        // 팔로우 관계 생성
        followService.createFollow(user1.getId(), user2.getId());
        followService.createFollow(user2.getId(), user1.getId());


        // 게시물 생성
        PostForm postForm = new PostForm();
        for (int i=1; i<4; i++) {
            postForm.setTitle("제목 " + i);
            postForm.setContent("내용 " + i);
            postService.createPost(postForm, user1.getId());
            postService.createPost(postForm, user2.getId());
        }

        // 빈 @PostConstruct 시점은 OSIV가 적용되는 시점이 아닌 듯
        // @Controller에서 프록시 객체의 조회 기능이 된 이유는 OSIV이기 때문
        // 여기선 OSIV가 안되니 프록시가 아닌 진짜 객체로만 조회해야함
        List<Post> user1Posts = postService.findPosts(user1.getId());
        List<Post> user2Posts = postService.findPosts(user2.getId());


        // 댓글 생성
        for (int i=1; i<4; i++) {
            commentService.createComment("댓글 "+i, user1.getId(), user2Posts.get(i-1).getId());
            commentService.createComment("댓글 "+i, user2.getId(), user1Posts.get(i-1).getId());
        }
    }
}
