package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "POST")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> postLikes;

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;

        user.getPosts().add(this);
    }


    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    // TODO
    // Comment @builder 생성 시 post는 이미 정해져 있어서 생성자 매개변수에 넣음
    // post setter는 당연히 필요없음(바뀔 필요가 없음). 그렇다면 post에서 comment를 추가하는 건 어디서 ?
    // comment 생성 시 post에 comment추가하는 코드 작성해야함
    // 당연히 연관관계 메소드는 모두 삭제. comment @builder 생성시에 모두 연결하는걸로
}
