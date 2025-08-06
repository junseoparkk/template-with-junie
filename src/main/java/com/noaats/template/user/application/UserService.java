package com.noaats.template.user.application;

import com.noaats.template.user.domain.User;
import com.noaats.template.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * User 도메인의 Use Case를 구현하는 서비스
 */
@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * ID로 사용자 조회
     */
    @Transactional(readOnly = true)
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
    }
    
    /**
     * 모든 사용자 조회
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * 새 사용자 생성
     */
    @Transactional
    public User createUser(String name, int age) {
        User user = new User(name, age);
        return userRepository.save(user);
    }
    
    /**
     * 사용자 정보 수정
     */
    @Transactional
    public User updateUser(String userId, String name, int age) {
        User user = getUser(userId);
        user.changeName(name);
        user.changeAge(age);
        return userRepository.save(user);
    }
    
    /**
     * 사용자 삭제
     */
    @Transactional
    public void deleteUser(String userId) {
        User user = getUser(userId);
        userRepository.delete(user);
    }
}