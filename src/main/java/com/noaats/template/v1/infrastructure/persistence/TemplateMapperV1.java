package com.noaats.template.v1.infrastructure.persistence;

import com.noaats.template.v1.domain.TemplateV1;
import org.springframework.stereotype.Component;

/**
 * TemplateV1 도메인 객체와 TemplateJpaEntityV1 간의 변환을 담당하는 매퍼
 */
@Component
public class TemplateMapperV1 {
    
    /**
     * TemplateV1 도메인 객체를 TemplateJpaEntityV1로 변환
     */
    public TemplateJpaEntityV1 toEntity(TemplateV1 template) {
        return TemplateJpaEntityV1.builder()
                .id(template.getId())
                .name(template.getName())
                .content(template.getContent())
                .build();
    }
    
    /**
     * TemplateJpaEntityV1를 TemplateV1 도메인 객체로 변환
     */
    public TemplateV1 toDomain(TemplateJpaEntityV1 entity) {
        return new TemplateV1(
                entity.getId(),
                entity.getName(),
                entity.getContent()
        );
    }
}