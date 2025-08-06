package com.noaats.template.user.infrastructure.persistence;

import com.noaats.template.user.domain.User;
import org.springframework.stereotype.Component;

/**
 * User 도메인 객체와 UserJpaEntity 간의 변환을 담당하는 매퍼
 */
@Component
public class UserMapper {
    
    /**
     * User 도메인 객체를 UserJpaEntity로 변환
     */
    public UserJpaEntity toEntity(User user) {
        return UserJpaEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .build();
    }
    
    /**
     * UserJpaEntity를 User 도메인 객체로 변환
     */
    public User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getAge()
        );
    }
}