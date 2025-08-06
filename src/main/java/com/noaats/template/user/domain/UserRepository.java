package com.noaats.template.user.domain;

import java.util.List;
import java.util.Optional;

/**
 * User 도메인 객체의 저장소 인터페이스
 * 도메인 중심 메서드 정의
 */
public interface UserRepository {
    
    /**
     * 사용자 저장
     */
    User save(User user);
    
    /**
     * ID로 사용자 조회
     */
    Optional<User> findById(String id);
    
    /**
     * 모든 사용자 조회
     */
    List<User> findAll();
    
    /**
     * 이름으로 사용자 조회
     */
    List<User> findByName(String name);
    
    /**
     * 사용자 삭제
     */
    void delete(User user);
}