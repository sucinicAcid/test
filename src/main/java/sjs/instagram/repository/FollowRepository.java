package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
