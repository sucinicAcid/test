package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Story;
import sjs.instagram.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class StoryRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired StoryRepository storyRepository;

    @Test
    void 스토리_생성() {
        User user = testDataFactory.createUser();
        Story story = new Story("photo", user);

        Story saved = storyRepository.save(story);

        assertThat(saved).isEqualTo(story);
    }

    @Test
    void 스토리_조회() {
        Story story1 = testDataFactory.createStory();
        Story story2 = testDataFactory.createStory();

        Story find = storyRepository.findById(story1.getId()).get();
        List<Story> findAll = storyRepository.findAll();

        assertThat(find).isEqualTo(story1);
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void 스토리_삭제() {
        Story story = testDataFactory.createStory();

        storyRepository.delete(story);

        long count = storyRepository.count();
        assertThat(count).isEqualTo(0);
    }
}