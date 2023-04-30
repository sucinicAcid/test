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

    @Embedded
    UserInfo userInfo;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy ="fromUser")
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy ="toUser")
    private List<Follow> followings = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Story> stories = new ArrayList<>();

    @OneToMany(mappedBy = "viewer")
    private List<StoryViewer> storyViewers = new ArrayList<>();

    public User(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void changeUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
