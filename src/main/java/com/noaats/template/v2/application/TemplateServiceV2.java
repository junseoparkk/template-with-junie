package com.noaats.template.v2.application;

import com.noaats.template.v2.domain.TemplateV2;
import com.noaats.template.v2.domain.TemplateRepositoryV2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Template 도메인의 Use Case를 구현하는 서비스
 */
@Service
public class TemplateServiceV2 {
    
    private final TemplateRepositoryV2 templateRepository;
    
    public TemplateServiceV2(TemplateRepositoryV2 templateRepository) {
        this.templateRepository = templateRepository;
    }
    
    /**
     * ID로 템플릿 조회
     */
    @Transactional(readOnly = true)
    public TemplateV2 findById(String templateId) {
        return templateRepository.findById(templateId)
                .orElseThrow(() -> new NoSuchElementException("Template not found with id: " + templateId));
    }
    
    /**
     * 모든 템플릿 조회
     */
    @Transactional(readOnly = true)
    public List<TemplateV2> findAll() {
        return templateRepository.findAll();
    }
    
    /**
     * 새 템플릿 생성
     */
    @Transactional
    public TemplateV2 create(String name, String content) {
        TemplateV2 template = new TemplateV2(name, content);
        if (!template.isValid()) {
            throw new IllegalArgumentException("Invalid template data");
        }
        return templateRepository.save(template);
    }
    
    /**
     * 템플릿 정보 수정
     */
    @Transactional
    public TemplateV2 update(String templateId, String name, String content) {
        TemplateV2 template = findById(templateId);
        template.update(name, content);
        if (!template.isValid()) {
            throw new IllegalArgumentException("Invalid template data");
        }
        return templateRepository.save(template);
    }
    
    /**
     * 템플릿 삭제
     */
    @Transactional
    public void delete(String templateId) {
        TemplateV2 template = findById(templateId);
        templateRepository.delete(template);
    }
    
    /**
     * 이름으로 템플릿 조회
     */
    @Transactional(readOnly = true)
    public List<TemplateV2> findByName(String name) {
        return templateRepository.findByName(name);
    }
}