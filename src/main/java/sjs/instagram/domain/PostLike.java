package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "POST_LIKE")
@Getter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_LIKE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    public void setPost(Post post) {
        if (this.post != null) {
            this.post.getPostLikes().remove(this);
        }
        this.post = post;
        if (!post.getPostLikes().contains(this)) {
            post.addPostLike(this);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
