package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "COMMNET_LIKE")
@Getter
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_LIKE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
