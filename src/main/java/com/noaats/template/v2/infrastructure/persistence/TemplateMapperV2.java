package com.noaats.template.v2.infrastructure.persistence;

import com.noaats.template.v2.domain.TemplateV2;
import org.springframework.stereotype.Component;

/**
 * Template 도메인 객체와 TemplateJpaEntity 간의 변환을 담당하는 매퍼
 */
@Component
public class TemplateMapperV2 {
    
    /**
     * Template 도메인 객체를 TemplateJpaEntity로 변환
     */
    public TemplateJpaEntityV2 toEntity(TemplateV2 template) {
        return TemplateJpaEntityV2.builder()
                .id(template.getId())
                .name(template.getName())
                .content(template.getContent())
                .build();
    }
    
    /**
     * TemplateJpaEntity를 Template 도메인 객체로 변환
     */
    public TemplateV2 toDomain(TemplateJpaEntityV2 entity) {
        return new TemplateV2(
                entity.getId(),
                entity.getName(),
                entity.getContent()
        );
    }
}