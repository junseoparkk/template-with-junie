package com.noaats.template.v2.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Template 도메인 객체의 JPA 엔티티
 */
@Entity
@Table(name = "templates")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateJpaEntityV2 {
    
    @Id
    private String id;
    
    private String name;
    
    private String content;
}