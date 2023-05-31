package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.Follow;
import sjs.instagram.domain.User;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFromUser(User user);
    List<Follow> findByToUser(User user);
}
