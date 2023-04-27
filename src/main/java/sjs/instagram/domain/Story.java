package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STORY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STORY_ID")
    private Long id;

    @Column(name = "PHOTO")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR")
    private User author;

    @OneToMany(mappedBy = "story")
    private List<StoryViewer> storyViewers = new ArrayList<>();

    public Story(String photo, User author) {
        this.photo = photo;
        this.author = author;

        author.getStories().add(this);
    }
}
