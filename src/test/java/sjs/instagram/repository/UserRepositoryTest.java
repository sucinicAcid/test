package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.User;
import sjs.instagram.domain.UserInfo;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Test
    void 사용자_생성() {
        UserInfo userInfo = new UserInfo("name", "photo", "instagram_id", "introduction");
        User user = new User(userInfo);
        user.setUsername("username");
        user.setPassword(passwordEncoder.encode("password"));

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
        assertThat(findAll.size()).isEqualTo(3);
    }

    @Test
    void 사용자_수정() {
        User user = testDataFactory.createUser();
        UserInfo userInfo = new UserInfo("new name", "new photo", "new instagram_id", "new introduction");
        user.changeUserInfo(userInfo);

        User updated = userRepository.save(user);

        UserInfo updatedUserInfo = updated.getUserInfo();
        assertThat(updatedUserInfo.getName()).isEqualTo("new name");
        assertThat(updatedUserInfo.getPhoto()).isEqualTo("new photo");
        assertThat(updatedUserInfo.getInstagram_id()).isEqualTo("new instagram_id");
        assertThat(updatedUserInfo.getIntroduction()).isEqualTo("new introduction");
    }

    @Test
    void 사용자_삭제() {
        User user = testDataFactory.createUser();

        userRepository.delete(user);

        long count = userRepository.count();
        assertThat(count).isEqualTo(1);
    }
}