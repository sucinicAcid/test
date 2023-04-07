package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "STORY_LIKE")
@Getter
public class StoryLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STORY_LIKE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STORY_ID")
    private Story story;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public void setStory(Story story) {
        this.story = story;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
