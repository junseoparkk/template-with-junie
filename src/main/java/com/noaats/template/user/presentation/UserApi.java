package com.noaats.template.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User API", description = "유저 관련 API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @Operation(summary = "유저 조회", description = "유저 ID로 단일 유저 정보 조회")
    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId);
    
    /**
     * 유저 응답 DTO
     */
    record UserResponse(String id, String name, int age) {
    }
}