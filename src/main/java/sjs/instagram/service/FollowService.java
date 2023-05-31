package sjs.instagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sjs.instagram.domain.Follow;
import sjs.instagram.domain.User;
import sjs.instagram.repository.FollowRepository;
import sjs.instagram.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public List<User> findFollowers(Long userId) {
        User user = userRepository.findById(userId).get();
        List<User> users = followRepository.findByToUser(user).stream()
                .map(f -> f.getFromUser())
                .collect(Collectors.toList());
        return users;
    }

    public List<User> findFollowings(Long userId) {
        User user = userRepository.findById(userId).get();
        List<User> users = followRepository.findByFromUser(user).stream()
                .map(f -> f.getToUser())
                .collect(Collectors.toList());
        return users;
    }

    @Transactional
    public void createFollow(Long fromUserId, Long toUserId) {
        User fromUser = userRepository.findById(fromUserId).get();
        User toUser = userRepository.findById(toUserId).get();
        // id말고 객체로 받으면 영속성 컨텍스트에 있는 엔티티가 아님
        // Follow 생성자 안에서 get함수를 호출해야 하기 때문에 엔티티여야만 함
        Follow follow = new Follow(fromUser, toUser);
        followRepository.save(follow);
    }
}
