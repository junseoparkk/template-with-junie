package com.noaats.template.user.domain;

import java.util.UUID;

/**
 * User 도메인 객체
 * 비즈니스 로직을 포함하는 POJO
 */
public class User {
    private final String id;
    private String name;
    private int age;

    /**
     * 새로운 User 생성
     * ID는 UUID를 사용하여 자동 생성
     */
    public User(String name, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
    }

    /**
     * 기존 User 로드
     */
    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * 사용자 이름 변경
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * 사용자 나이 변경
     */
    public void changeAge(int age) {
        this.age = age;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}