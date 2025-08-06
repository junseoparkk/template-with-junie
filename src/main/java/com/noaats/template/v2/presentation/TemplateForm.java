package com.noaats.template.v2.presentation;

import jakarta.validation.constraints.NotBlank;

public record TemplateForm(
    @NotBlank String name,
    @NotBlank String content
) {}
