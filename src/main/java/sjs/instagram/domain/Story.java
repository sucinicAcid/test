package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STORY")
@Getter
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STORY_ID")
    private Long id;

    @Column(name = "PHOTO")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private User author;

    @OneToMany(mappedBy = "story", fetch = FetchType.LAZY)
    private List<StoryViewer> storyViewers = new ArrayList<>();


    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAuthor(User author) {
        if (this.author != null) {
            this.author.getStories().remove(this);
        }
        this.author = author;
        if (!author.getStories().contains(this)) {
            author.addStory(this);
        }
    }

    public void addStoryViewer(StoryViewer storyViewer) {
        this.getStoryViewers().add(storyViewer);
        if (storyViewer.getStory() != this) {
            storyViewer.setStory(this);
        }
    }
}
