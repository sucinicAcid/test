package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMMENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<Reply> replies = new ArrayList<>();

    /**
     * Comment 생성 시에는 content,user,post가 이미 확정
     * 나머지 필드는 아직 없음
     */
    @Builder
    public Comment(String content, User user, Post post) {
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        if (this.post != null) {
            this.post.getComments().remove(this);
        }
        this.post = post;
        if (!post.getComments().contains(this)) {
            post.addComment(this);
        }
    }

    public void addCommentLike(CommentLike commentLike) {
        this.commentLikes.add(commentLike);
        if (commentLike.getComment() != this) {
            commentLike.setComment(this);
        }
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
        if (reply.getComment() != this) {
            reply.setComment(this);
        }
    }
}
