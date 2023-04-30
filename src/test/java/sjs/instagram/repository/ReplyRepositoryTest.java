package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Comment;
import sjs.instagram.domain.Reply;
import sjs.instagram.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class ReplyRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired ReplyRepository replyRepository;

    @Test
    void 답글_생성() {
        Comment comment = testDataFactory.createComment();
        User user = testDataFactory.createUser();
        Reply reply = new Reply("content", user, comment);

        Reply saved = replyRepository.save(reply);

        assertThat(saved).isEqualTo(reply);
    }

    @Test
    void 답글_조회() {
        Reply reply1 = testDataFactory.createReply();
        Reply reply2 = testDataFactory.createReply();

        Reply find = replyRepository.findById(reply1.getId()).get();
        List<Reply> findAll = replyRepository.findAll();

        assertThat(find).isEqualTo(reply1);
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void 답글_삭제() {
        Reply reply = testDataFactory.createReply();

        replyRepository.delete(reply);

        long count = replyRepository.count();
        assertThat(count).isEqualTo(0);
    }
}