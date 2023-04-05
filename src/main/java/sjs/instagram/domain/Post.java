package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    private User user;
}
