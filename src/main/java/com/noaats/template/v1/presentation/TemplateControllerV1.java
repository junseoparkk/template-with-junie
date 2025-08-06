package com.noaats.template.v1.presentation;

import com.noaats.template.v1.application.TemplateServiceV1;
import com.noaats.template.v1.domain.TemplateV1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateControllerV1 implements TemplateApiV1 {

    private final TemplateServiceV1 templateService;

    public TemplateControllerV1(TemplateServiceV1 templateService) {
        this.templateService = templateService;
    }

    @Override
    public ResponseEntity<TemplateResponseV1> getTemplate(@PathVariable String templateId) {
        TemplateV1 template = templateService.getTemplate(templateId);
        return ResponseEntity.ok(new TemplateResponseV1(
                template.getId(),
                template.getName(),
                template.getContent()
        ));
    }
}