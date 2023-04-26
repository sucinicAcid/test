package sjs.instagram.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FOLLOW")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FOLLOW_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_USER")
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_USER")
    private User toUser;

    public Follow(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;

        fromUser.getFollowings().add(this);
        toUser.getFollowers().add(this);
    }


    /**
     * 정적 팩토리 메소드로 한 이유
     * 1. Follow가 한번 만들어진 이상 User는 바뀔필요 X -> set 안쓸려고
     * 2. Follow 필드 모두 Follow 생성 시에 들어감 -> 필드 바꾸는거 방지

    public static Follow createFollow(User fromUser, User toUser) {
        Follow follow = new Follow();
        follow.fromUser = fromUser;
        follow.toUser = toUser;

        fromUser.getFollowings().add(follow);
        toUser.getFollowers().add(follow);
        return follow;
    }

    protected Follow() {

    }
    */
}
