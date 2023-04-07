package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class StoryViewer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STORY_VIEWER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VIEWER")
    private User viewer;

    @ManyToOne
    @JoinColumn(name = "STORY_ID")
    private Story story;
}
