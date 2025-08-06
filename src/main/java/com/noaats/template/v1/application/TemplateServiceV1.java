package com.noaats.template.v1.application;

import com.noaats.template.v1.domain.TemplateV1;
import com.noaats.template.v1.domain.TemplateRepositoryV1;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * TemplateV1 도메인의 Use Case를 구현하는 서비스
 */
@Service
public class TemplateServiceV1 {
    
    private final TemplateRepositoryV1 templateRepository;
    
    public TemplateServiceV1(TemplateRepositoryV1 templateRepository) {
        this.templateRepository = templateRepository;
    }
    
    /**
     * ID로 템플릿 조회
     */
    @Transactional(readOnly = true)
    public TemplateV1 getTemplate(String templateId) {
        return templateRepository.findById(templateId)
                .orElseThrow(() -> new NoSuchElementException("TemplateV1 not found with id: " + templateId));
    }
    
    /**
     * 모든 템플릿 조회
     */
    @Transactional(readOnly = true)
    public List<TemplateV1> getAllTemplates() {
        return templateRepository.findAll();
    }
    
    /**
     * 새 템플릿 생성
     */
    @Transactional
    public TemplateV1 createTemplate(String name, String content) {
        TemplateV1 template = new TemplateV1(name, content);
        return templateRepository.save(template);
    }
    
    /**
     * 템플릿 정보 수정
     */
    @Transactional
    public TemplateV1 updateTemplate(String templateId, String name, String content) {
        TemplateV1 template = getTemplate(templateId);
        template.changeName(name);
        template.changeContent(content);
        return templateRepository.save(template);
    }
    
    /**
     * 템플릿 삭제
     */
    @Transactional
    public void deleteTemplate(String templateId) {
        TemplateV1 template = getTemplate(templateId);
        templateRepository.delete(template);
    }
}