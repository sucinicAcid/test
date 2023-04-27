package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Comment;
import sjs.instagram.domain.CommentLike;
import sjs.instagram.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentLikeRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired CommentLikeRepository commentLikeRepository;

    @Test
    void 댓글_좋아요_생성() {
        Comment comment = testDataFactory.createComment();
        User user = testDataFactory.createUser();
        CommentLike commentLike = new CommentLike(comment, user);

        CommentLike saved = commentLikeRepository.save(commentLike);

        assertThat(saved).isEqualTo(commentLike);
    }

    @Test
    void 댓글_좋아요_조회() {
        CommentLike commentLike1 = testDataFactory.createCommentLike();
        CommentLike commentLike2 = testDataFactory.createCommentLike();

        CommentLike find = commentLikeRepository.findById(commentLike1.getId()).get();
        List<CommentLike> findAll = commentLikeRepository.findAll();

        assertThat(find).isEqualTo(commentLike1);
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void 댓글_좋아요_삭제() {
        CommentLike commentLike = testDataFactory.createCommentLike();

        commentLikeRepository.delete(commentLike);

        long count = commentLikeRepository.count();
        assertThat(count).isEqualTo(0);
    }
}