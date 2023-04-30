package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Story;
import sjs.instagram.domain.StoryViewer;
import sjs.instagram.domain.User;

import java.util.List;

@SpringBootTest
@Transactional
class StoryViewerRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired StoryViewerRepository storyViewerRepository;

    @Test
    void 스토리_조회자_생성() {
        User user = testDataFactory.createUser();
        Story story = testDataFactory.createStory();
        StoryViewer storyViewer = new StoryViewer(user, story);

        StoryViewer saved = storyViewerRepository.save(storyViewer);

        assertThat(saved).isEqualTo(storyViewer);
    }

    @Test
    void 스토리_조회자_조회() {
        StoryViewer storyViewer1 = testDataFactory.createStoryViewer();
        StoryViewer storyViewer2 = testDataFactory.createStoryViewer();

        StoryViewer find = storyViewerRepository.findById(storyViewer1.getId()).get();
        List<StoryViewer> findAll = storyViewerRepository.findAll();

        assertThat(find).isEqualTo(storyViewer1);
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void 스토리_조회자_삭제() {
        StoryViewer storyViewer = testDataFactory.createStoryViewer();

        storyViewerRepository.delete(storyViewer);

        long count = storyViewerRepository.count();
        assertThat(count).isEqualTo(0);
    }
}