package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Comment;
import sjs.instagram.domain.CommentLike;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired CommentRepository commentRepository;

    @Test
    void 댓글_생성() {
        User user = testDataFactory.createUser();
        Post post = testDataFactory.createPost();
        Comment comment = new Comment("content", user, post);

        Comment saved = commentRepository.save(comment);

        assertThat(saved).isEqualTo(comment);
    }

    @Test
    void 댓글_조회() {
        Comment comment1 = testDataFactory.createComment();
        Comment comment2 = testDataFactory.createComment();

        Comment find = commentRepository.findById(comment1.getId()).get();
        List<Comment> findAll = commentRepository.findAll();

        assertThat(find).isEqualTo(comment1);
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void 댓글_수정() {
        Comment comment = testDataFactory.createComment();

        comment.changeContent("new content");
        Comment updated = commentRepository.save(comment);

        assertThat(updated.getContent()).isEqualTo("new content");
    }
    @Test
    void 댓글_삭제() {
        Comment comment = testDataFactory.createComment();

        commentRepository.delete(comment);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(0);
    }
}