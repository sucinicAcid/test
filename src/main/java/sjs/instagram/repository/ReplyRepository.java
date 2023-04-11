package sjs.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sjs.instagram.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
