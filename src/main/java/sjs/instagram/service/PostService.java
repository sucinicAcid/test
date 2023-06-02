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

    public Post findPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    public List<Post> findPosts(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Post> posts = postRepository.findPostsByUser(user);
        return posts;
    }

    @Transactional
    public Post updatePost(PostForm postForm, Long postId) {
        Post post = postRepository.findById(postId).get();
        post.changeTitle(postForm.getTitle());
        post.changeContent(postForm.getContent());
        return post;
    }
}
