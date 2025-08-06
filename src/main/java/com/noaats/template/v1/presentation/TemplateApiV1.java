package com.noaats.template.v1.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Template API V1", description = "템플릿 관련 API V1")
@RequestMapping("/api/v1/templates")
public interface TemplateApiV1 {

    @Operation(summary = "템플릿 조회", description = "템플릿 ID로 단일 템플릿 정보 조회")
    @GetMapping("/{templateId}")
    ResponseEntity<TemplateResponseV1> getTemplate(@PathVariable("templateId") String templateId);
    
    /**
     * 템플릿 응답 DTO
     */
    record TemplateResponseV1(String id, String name, String content) {
    }
}