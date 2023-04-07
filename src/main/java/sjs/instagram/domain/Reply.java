package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

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
}
