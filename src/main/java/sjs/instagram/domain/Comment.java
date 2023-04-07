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
}
