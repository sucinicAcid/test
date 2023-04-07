package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMMENT")
@Getter
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
    private List<Reply> replies;



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
