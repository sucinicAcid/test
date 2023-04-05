package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Story {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String photo;

    @ManyToOne
    private User author;
    @OneToMany(mappedBy = "auth", fetch = FetchType.LAZY)
    private List<StoryViewer> viewers;
}
