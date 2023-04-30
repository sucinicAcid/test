package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired PostRepository postRepository;

    @Test
    void 게시물_생성() {
        User user = testDataFactory.createUser();
        Post post = new Post("title", "content", user);

        Post saved = postRepository.save(post);

        assertThat(saved).isEqualTo(post);
    }

    @Test
    void 게시물_조회() {
        Post post1 = testDataFactory.createPost();
        Post post2 = testDataFactory.createPost();

        Post find = postRepository.findById(post1.getId()).get();
        List<Post> findAll = postRepository.findAll();

        assertThat(find).isEqualTo(post1);
        assertThat(findAll.size()).isEqualTo(2);
    }


    @Test
    void 게시물_수정() {
        Post post = testDataFactory.createPost();
        post.changeTitle("new title");
        post.changeContent("new content");

        Post updated = postRepository.save(post);

        assertThat(updated.getTitle()).isEqualTo("new title");
        assertThat(updated.getContent()).isEqualTo("new content");
    }

    @Test
    void 게시물_삭제() {
        Post post = testDataFactory.createPost();

        postRepository.delete(post);

        long count = postRepository.count();
        assertThat(count).isEqualTo(0);
    }
}