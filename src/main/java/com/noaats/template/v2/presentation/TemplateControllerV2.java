package com.noaats.template.v2.presentation;

import com.noaats.template.v2.application.TemplateServiceV2;
import com.noaats.template.v2.domain.TemplateV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/templates")
public class TemplateControllerV2 implements TemplateApiV2 {

    private final TemplateServiceV2 templateServiceV2;

    public TemplateControllerV2(TemplateServiceV2 templateServiceV2) {
        this.templateServiceV2 = templateServiceV2;
    }

    @Override
    public ResponseEntity<TemplateResponse> getTemplate(@PathVariable String templateId) {
        TemplateV2 template = templateServiceV2.findById(templateId);
        return ResponseEntity.ok(mapToResponse(template));
    }

    @Override
    public ResponseEntity<List<TemplateResponse>> getAllTemplates() {
        List<TemplateV2> templates = templateServiceV2.findAll();
        List<TemplateResponse> responses = templates.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<TemplateResponse> createTemplate(@RequestBody TemplateRequest request) {
        TemplateV2 template = templateServiceV2.create(request.name(), request.content());
        return ResponseEntity.ok(mapToResponse(template));
    }

    @Override
    public ResponseEntity<TemplateResponse> updateTemplate(
            @PathVariable String templateId,
            @RequestBody TemplateRequest request) {
        TemplateV2 template = templateServiceV2.update(templateId, request.name(), request.content());
        return ResponseEntity.ok(mapToResponse(template));
    }

    @Override
    public ResponseEntity<Void> deleteTemplate(@PathVariable String templateId) {
        templateServiceV2.delete(templateId);
        return ResponseEntity.noContent().build();
    }

    private TemplateResponse mapToResponse(TemplateV2 template) {
        return new TemplateResponse(
                template.getId(),
                template.getName(),
                template.getContent()
        );
    }
}