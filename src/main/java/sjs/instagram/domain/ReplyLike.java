package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "REPLY_LIKE")
@Getter
public class ReplyLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REPLY_LIKE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "REPLY_ID")
    private Reply reply;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
