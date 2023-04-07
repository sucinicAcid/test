package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "NAME")
    private String username;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "INSTAGRAM_ID")
    private String instagram_id;

    @Column(name = "INTRODUCTION")
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
    private List<Story> stories = new ArrayList<>();
}
