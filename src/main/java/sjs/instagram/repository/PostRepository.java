package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.Post;
import sjs.instagram.domain.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUser(User user);
}
