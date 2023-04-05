package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String photo;
    private String instagram_id;
    private String introduction;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy ="fromUser", fetch = FetchType.LAZY)
    private List<Follow> followers = new ArrayList<>();
    @OneToMany(mappedBy ="toUser", fetch = FetchType.LAZY)
    private List<Follow> followings = new ArrayList<>();

    @OneToMany(mappedBy = "viewer", fetch = FetchType.LAZY)
    private List<StoryViewer> storyViewers = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Story> stories;
}
