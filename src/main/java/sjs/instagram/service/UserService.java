package sjs.instagram.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sjs.instagram.domain.User;
import sjs.instagram.domain.UserInfo;
import sjs.instagram.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void joinUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Transactional(readOnly = true)
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserByInstagram_id(String instagram_id) {
        return userRepository.findByUserInfoInstagramId(instagram_id);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    public void changeUserInfo(Long userId, UserInfo userInfo) {
        User user = userRepository.findById(userId).get();
        user.changeUserInfo(userInfo);
    }
}
