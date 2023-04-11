package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
