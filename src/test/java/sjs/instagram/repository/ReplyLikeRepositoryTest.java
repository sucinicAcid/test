package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Reply;
import sjs.instagram.domain.ReplyLike;
import sjs.instagram.domain.User;


@SpringBootTest
@Transactional
class ReplyLikeRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired ReplyLikeRepository replyLikeRepository;

    @Test
    void 답글_좋아요_생성() {
        Reply reply = testDataFactory.createReply();
        User user = testDataFactory.createUser();
        ReplyLike replyLike = new ReplyLike(reply, user);

        ReplyLike saved = replyLikeRepository.save(replyLike);

        Assertions.assertThat(saved).isEqualTo(replyLike);
    }

    @Test
    void 답글_좋아요_조회() {
        ReplyLike replyLike1 = testDataFactory.createReplyLike();
        ReplyLike replyLike2 = testDataFactory.createReplyLike();

        
    }

    @Test
    void 답글_좋아요_삭제() {

    }
}