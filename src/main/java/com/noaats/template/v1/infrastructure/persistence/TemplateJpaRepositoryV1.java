package com.noaats.template.v1.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TemplateJpaEntityV1를 위한 Spring Data JPA Repository
 */
@Repository
public interface TemplateJpaRepositoryV1 extends JpaRepository<TemplateJpaEntityV1, String> {
    
    /**
     * 이름으로 템플릿 조회
     */
    List<TemplateJpaEntityV1> findByName(String name);
}