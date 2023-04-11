package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
