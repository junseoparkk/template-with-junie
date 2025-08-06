package com.noaats.template.v2.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TemplateJpaEntity를 위한 Spring Data JPA Repository
 */
@Repository
public interface TemplateJpaRepositoryV2 extends JpaRepository<TemplateJpaEntityV2, String> {
    
    /**
     * 이름으로 템플릿 조회
     */
    List<TemplateJpaEntityV2> findByName(String name);
}