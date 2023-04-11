package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
