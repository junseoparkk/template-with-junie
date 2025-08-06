package com.noaats.template.user.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserJpaEntity를 위한 Spring Data JPA Repository
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {
    
    /**
     * 이름으로 사용자 조회
     */
    List<UserJpaEntity> findByName(String name);
}