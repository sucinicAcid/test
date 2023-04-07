package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
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

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Story> stories = new ArrayList<>();

    @OneToMany(mappedBy = "viewer", fetch = FetchType.LAZY)
    private List<StoryViewer> storyViewers = new ArrayList<>();


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setInstagram_id(String instagram_id) {
        this.instagram_id = instagram_id;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void addPost(Post post) {
        this.getPosts().add(post);
        if (post.getUser() != this) {
            post.setUser(this);
        }
    }

    public void addFollower(Follow follow) {
        this.getFollowers().add(follow);
        if (follow.getToUser() != this) {
            follow.setToUser(this);
        }
    }

    public void addFollowing(Follow follow) {
        this.getFollowings().add(follow);
        if (follow.getFromUser() != this) {
            follow.setFromUser(this);
        }
    }

    public void addStory(Story story) {
        this.stories.add(story);
        if (story.getAuthor() != this) {
            story.setAuthor(this);
        }
    }

    public void addStoryViewer(StoryViewer storyViewer) {
        this.storyViewers.add(storyViewer);
        if (storyViewer.getViewer() != this) {
            storyViewer.setViewer(this);
        }
    }
}
