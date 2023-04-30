package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Follow;
import sjs.instagram.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FollowRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired FollowRepository followRepository;

    @Test
    void 팔로우_생성() {
        User fromUser = testDataFactory.createUser();
        User toUser = testDataFactory.createUser();
        Follow follow = new Follow(fromUser, toUser);

        Follow saved = followRepository.save(follow);

        assertThat(saved).isEqualTo(follow);
    }

    @Test
    void 팔로우_조회() {
        Follow follow1 = testDataFactory.createFollow();
        Follow follow2 = testDataFactory.createFollow();

        Follow find = followRepository.findById(follow1.getId()).get();
        List<Follow> findAll = followRepository.findAll();

        assertThat(find).isEqualTo(follow1);
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void 팔로우_삭제() {
        Follow follow = testDataFactory.createFollow();

        followRepository.delete(follow);

        long count = followRepository.count();
        assertThat(count).isEqualTo(0);
    }
}