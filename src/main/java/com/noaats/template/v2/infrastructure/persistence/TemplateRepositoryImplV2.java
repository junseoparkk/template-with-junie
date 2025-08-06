package com.noaats.template.v2.infrastructure.persistence;

import com.noaats.template.v2.domain.TemplateV2;
import com.noaats.template.v2.domain.TemplateRepositoryV2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TemplateRepository 인터페이스의 JPA 구현체
 */
@Repository
public class TemplateRepositoryImplV2 implements TemplateRepositoryV2 {
    
    private final TemplateJpaRepositoryV2 templateJpaRepositoryV2;
    private final TemplateMapperV2 templateMapperV2;
    
    public TemplateRepositoryImplV2(TemplateJpaRepositoryV2 templateJpaRepositoryV2, TemplateMapperV2 templateMapperV2) {
        this.templateJpaRepositoryV2 = templateJpaRepositoryV2;
        this.templateMapperV2 = templateMapperV2;
    }
    
    @Override
    public TemplateV2 save(TemplateV2 template) {
        TemplateJpaEntityV2 savedEntity = templateJpaRepositoryV2.save(templateMapperV2.toEntity(template));
        return templateMapperV2.toDomain(savedEntity);
    }
    
    @Override
    public Optional<TemplateV2> findById(String id) {
        return templateJpaRepositoryV2.findById(id)
                .map(templateMapperV2::toDomain);
    }
    
    @Override
    public List<TemplateV2> findAll() {
        return templateJpaRepositoryV2.findAll().stream()
                .map(templateMapperV2::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TemplateV2> findByName(String name) {
        return templateJpaRepositoryV2.findByName(name).stream()
                .map(templateMapperV2::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void delete(TemplateV2 template) {
        templateJpaRepositoryV2.deleteById(template.getId());
    }
}