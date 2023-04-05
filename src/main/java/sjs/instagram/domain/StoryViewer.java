package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class StoryViewer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User viewer;
    @ManyToOne
    private Story story;
}
