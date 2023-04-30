package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.TestDataFactory;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.PostLike;
import sjs.instagram.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class PostLikeRepositoryTest {

    @Autowired TestDataFactory testDataFactory;
    @Autowired PostLikeRepository postLikeRepository;

    @Test
    void 게시물_좋아요_생성() {
        Post post = testDataFactory.createPost();
        User user = testDataFactory.createUser();
        PostLike postLike = new PostLike(post, user);

        PostLike saved = postLikeRepository.save(postLike);

        assertThat(saved).isEqualTo(postLike);
    }

    @Test
    void 게시물_좋아요_조회() {
        PostLike postLike1 = testDataFactory.createPostLike();
        PostLike postLike2 = testDataFactory.createPostLike();

        PostLike find = postLikeRepository.findById(postLike1.getId()).get();
        List<PostLike> findAll = postLikeRepository.findAll();

        assertThat(find).isEqualTo(postLike1);
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void 게시물_좋아요_삭제() {
        PostLike postLike = testDataFactory.createPostLike();

        postLikeRepository.delete(postLike);

        long count = postLikeRepository.count();
        assertThat(count).isEqualTo(0);
    }
}