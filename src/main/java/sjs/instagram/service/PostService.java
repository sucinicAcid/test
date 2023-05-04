package sjs.instagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;
import sjs.instagram.form.PostForm;
import sjs.instagram.repository.PostRepository;
import sjs.instagram.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createPost(PostForm postForm, Long userId) {
        User user = userRepository.findById(userId).get();
        Post post = new Post(postForm.getTitle(), postForm.getContent(), user);
        postRepository.save(post);
    }

    public List<Post> findPosts(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getPosts();
    }
}
