package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired UserRepository userRepository;

    @Test
    void 사용자_생성() {
        User user = new User("username", "photo", "instagram_id", "introduction");

        User saved = userRepository.save(user);

        assertThat(saved).isEqualTo(user);
    }

    @Test
    void 사용자_조회() {
        User user1 = testDataFactory.createUser();
        User user2 = testDataFactory.createUser();

        User find = userRepository.findById(user1.getId()).get();
        List<User> findAll = userRepository.findAll();

        assertThat(find).isEqualTo(user1);
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void 사용자_수정() {
        User user = testDataFactory.createUser();
        user.changeUsername("new username");
        user.changePhoto("new photo");
        user.changeInstagram_id("new instagram_id");
        user.changeIntroduction("new introduction");

        User updated = userRepository.save(user);

        assertThat(updated.getUsername()).isEqualTo("new username");
        assertThat(updated.getPhoto()).isEqualTo("new photo");
        assertThat(updated.getInstagram_id()).isEqualTo("new instagram_id");
        assertThat(updated.getIntroduction()).isEqualTo("new introduction");
    }

    @Test
    void 사용자_삭제() {
        User user = testDataFactory.createUser();

        userRepository.delete(user);

        long count = userRepository.count();
        assertThat(count).isEqualTo(0);
    }
}