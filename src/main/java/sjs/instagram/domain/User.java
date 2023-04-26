package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Story> stories = new ArrayList<>();

    @OneToMany(mappedBy = "viewer", fetch = FetchType.LAZY)
    private List<StoryViewer> storyViewers = new ArrayList<>();

    public User(String username, String photo, String instagram_id, String introduction) {
        this.username = username;
        this.photo = photo;
        this.instagram_id = instagram_id;
        this.introduction = introduction;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changePhoto(String photo) {
        this.photo = photo;
    }

    public void changeInstagram_id(String instagram_id) {
        this.instagram_id = instagram_id;
    }

    public void changeIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
