package sjs.instagram.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sjs.instagram.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @BeforeAll
    static void beforeAll(@Autowired CommentRepository commentRepository) {
        for (int i=0; i<5; i++) {
            Comment comment = Comment.builder()
                    .content("content" + i + 1)
                    .post(null)
                    .user(null)
                    .build();
            commentRepository.save(comment);
        }
    }

    @AfterAll
    static void afterAll(@Autowired CommentRepository commentRepository) {
        commentRepository.deleteAll();
    }

    @Test
    void basicCRUD() {
        // Create
        Comment comment = Comment.builder()
                .content("content")
                .post(null)
                .user(null)
                .build();
        Comment saved = commentRepository.save(comment);

        assertThat(saved).isEqualTo(comment);
        
        // Read
        Comment find = commentRepository.findById(saved.getId()).get();
        List<Comment> findAll = commentRepository.findAll();

        assertThat(find).isEqualTo(saved);
        assertThat(findAll.size()).isEqualTo(6);

        // Update
        find.setContent("content update");
        Comment updated = commentRepository.save(find);

        assertThat(updated).isEqualTo(find);

        // Delete
        commentRepository.delete(comment);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(5);
    }
}