package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne
    private User user;
    @ManyToOne
    private Comment comment;
}
