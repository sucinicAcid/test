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



    public void setReply(Reply reply) {
        if (this.reply != null) {
            this.reply.getReplyLikes().remove(this);
        }
        this.reply = reply;
        if (!reply.getReplyLikes().contains(this)) {
            reply.addReplyLike(this);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
