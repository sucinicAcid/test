package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.StoryViewer;

public interface StoryViewerRepository extends JpaRepository<StoryViewer, Long> {
}
