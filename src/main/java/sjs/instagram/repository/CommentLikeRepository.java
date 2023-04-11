package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.CommentLike;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
}
