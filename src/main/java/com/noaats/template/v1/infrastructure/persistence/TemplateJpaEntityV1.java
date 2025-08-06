package com.noaats.template.v1.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TemplateV1 도메인 객체의 JPA 엔티티
 */
@Entity
@Table(name = "templates")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateJpaEntityV1 {
    
    @Id
    private String id;
    
    private String name;
    
    private String content;
}