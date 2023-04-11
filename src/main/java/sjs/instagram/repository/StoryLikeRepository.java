package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.StoryLike;

public interface StoryLikeRepository extends JpaRepository<StoryLike, Long> {
}
