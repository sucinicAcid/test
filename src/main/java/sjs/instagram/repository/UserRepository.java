package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    public User findByUserInfoInstagramId(String instagram_id);
}
