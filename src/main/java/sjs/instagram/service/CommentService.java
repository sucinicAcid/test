package sjs.instagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sjs.instagram.domain.Comment;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;
import sjs.instagram.repository.CommentRepository;
import sjs.instagram.repository.PostRepository;
import sjs.instagram.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createComment(String content, Long userId, Long postId) {
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();
        Comment comment = new Comment(content, user, post);
        commentRepository.save(comment);
    }
}
