package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "REPLY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Reply(String content, User user, Comment comment) {
        this.content = content;
        this.user = user;
        this.comment = comment;

        comment.getReplies().add(this);
    }

    public void addReplyLike(ReplyLike replyLike) {
        this.getReplyLikes().add(replyLike);
        if (replyLike.getReply() != this) {
            replyLike.setReply(this);
        }
    }
}
