package com.noaats.template.v2.domain;

import java.util.UUID;

/**
 * Template 도메인 객체
 * 비즈니스 로직을 포함하는 POJO
 */
public class TemplateV2 {
    private final String id;
    private String name;
    private String content;

    /**
     * 새로운 Template 생성
     * ID는 UUID를 사용하여 자동 생성
     */
    public TemplateV2(String name, String content) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.content = content;
    }

    /**
     * 기존 Template 로드
     */
    public TemplateV2(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    /**
     * 템플릿 이름 변경
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * 템플릿 내용 변경
     */
    public void changeContent(String content) {
        this.content = content;
    }

    /**
     * 템플릿 정보 업데이트
     */
    public void update(String name, String content) {
        this.name = name;
        this.content = content;
    }

    /**
     * 템플릿 내용 검증
     * @return 유효한 템플릿인지 여부
     */
    public boolean isValid() {
        return name != null && !name.isBlank() && content != null;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}