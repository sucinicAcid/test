package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<CommentLike> commentLikes = new ArrayList<>();
}
