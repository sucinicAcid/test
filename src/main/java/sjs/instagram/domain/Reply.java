package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "REPLY")
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REPLY_ID")
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    @OneToMany(mappedBy = "reply", fetch = FetchType.LAZY)
    private List<ReplyLike> replyLikes;



    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(Comment comment) {
        if (this.comment != null) {
            this.comment.getReplies().remove(this);
        }
        this.comment = comment;
        if (!comment.getReplies().contains(this)) {
            comment.addReply(this);
        }
    }

    public void addReplyLike(ReplyLike replyLike) {
        this.getReplyLikes().add(replyLike);
        if (replyLike.getReply() != this) {
            replyLike.setReply(this);
        }
    }
}
