package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public StoryViewer(User viewer, Story story) {
        this.viewer = viewer;
        this.story = story;

        story.getStoryViewers().add(this);
        viewer.getStoryViewers().add(this);
    }
}
