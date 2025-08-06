package com.noaats.template.v2.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Template API", description = "템플릿 관련 API")
public interface TemplateApiV2 {

    @Operation(summary = "템플릿 조회", description = "템플릿 ID로 단일 템플릿 정보 조회")
    @GetMapping("/{templateId}")
    ResponseEntity<TemplateResponse> getTemplate(@PathVariable("templateId") String templateId);

    @Operation(summary = "템플릿 목록 조회", description = "모든 템플릿 목록 조회")
    @GetMapping
    ResponseEntity<List<TemplateResponse>> getAllTemplates();

    @Operation(summary = "템플릿 등록", description = "새로운 템플릿 등록")
    @PostMapping
    ResponseEntity<TemplateResponse> createTemplate(@RequestBody TemplateRequest request);

    @Operation(summary = "템플릿 수정", description = "기존 템플릿 정보 수정")
    @PutMapping("/{templateId}")
    ResponseEntity<TemplateResponse> updateTemplate(
            @PathVariable("templateId") String templateId,
            @RequestBody TemplateRequest request);

    @Operation(summary = "템플릿 삭제", description = "템플릿 삭제")
    @DeleteMapping("/{templateId}")
    ResponseEntity<Void> deleteTemplate(@PathVariable("templateId") String templateId);

    /**
     * 템플릿 요청 DTO
     */
    record TemplateRequest(String name, String content) {
    }

    /**
     * 템플릿 응답 DTO
     */
    record TemplateResponse(String id, String name, String content) {
    }
}