package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMMENT_LIKE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public CommentLike(Comment comment, User user) {
        this.comment = comment;
        this.user = user;

        comment.getCommentLikes().add(this);
    }
}
