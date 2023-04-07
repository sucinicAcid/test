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



    public void setViewer(User viewer) {
        if (this.viewer != null) {
            this.viewer.getStoryViewers().remove(this);
        }
        this.viewer = viewer;
        if (!viewer.getStoryViewers().contains(this)) {
            viewer.addStoryViewer(this);
        }
    }

    public void setStory(Story story) {
        if (this.story != null) {
            this.story.getStoryViewers().remove(this);
        }
        this.story = story;
        if (!story.getStoryViewers().contains(this)) {
            story.addStoryViewer(this);
        }
    }
}
