package com.noaats.template.v1.domain;

import java.util.List;
import java.util.Optional;

/**
 * TemplateV1 도메인 객체의 저장소 인터페이스
 * 도메인 중심 메서드 정의
 */
public interface TemplateRepositoryV1 {
    
    /**
     * 템플릿 저장
     */
    TemplateV1 save(TemplateV1 template);
    
    /**
     * ID로 템플릿 조회
     */
    Optional<TemplateV1> findById(String id);
    
    /**
     * 모든 템플릿 조회
     */
    List<TemplateV1> findAll();
    
    /**
     * 이름으로 템플릿 조회
     */
    List<TemplateV1> findByName(String name);
    
    /**
     * 템플릿 삭제
     */
    void delete(TemplateV1 template);
}