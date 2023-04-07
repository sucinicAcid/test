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
    private List<StoryViewer> viewers = new ArrayList<>();
}
