package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REPLY_LIKE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public ReplyLike(Reply reply, User user) {
        this.reply = reply;
        this.user = user;

        reply.getReplyLikes().add(this);
    }
}
