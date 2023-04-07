package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "COMMENT_LIKE")
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



    public void setComment(Comment comment) {
        if (this.comment != null) {
            this.comment.getCommentLikes().remove(this);
        }
        this.comment = comment;
        if (!comment.getCommentLikes().contains(this)) {
            comment.addCommentLike(this);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
