package com.noaats.template.v1.infrastructure.persistence;

import com.noaats.template.v1.domain.TemplateV1;
import com.noaats.template.v1.domain.TemplateRepositoryV1;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TemplateRepositoryV1 인터페이스의 JPA 구현체
 */
@Repository
public class TemplateRepositoryImplV1 implements TemplateRepositoryV1 {
    
    private final TemplateJpaRepositoryV1 templateJpaRepository;
    private final TemplateMapperV1 templateMapper;
    
    public TemplateRepositoryImplV1(TemplateJpaRepositoryV1 templateJpaRepository, TemplateMapperV1 templateMapper) {
        this.templateJpaRepository = templateJpaRepository;
        this.templateMapper = templateMapper;
    }
    
    @Override
    public TemplateV1 save(TemplateV1 template) {
        TemplateJpaEntityV1 savedEntity = templateJpaRepository.save(templateMapper.toEntity(template));
        return templateMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<TemplateV1> findById(String id) {
        return templateJpaRepository.findById(id)
                .map(templateMapper::toDomain);
    }
    
    @Override
    public List<TemplateV1> findAll() {
        return templateJpaRepository.findAll().stream()
                .map(templateMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TemplateV1> findByName(String name) {
        return templateJpaRepository.findByName(name).stream()
                .map(templateMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void delete(TemplateV1 template) {
        templateJpaRepository.deleteById(template.getId());
    }
}