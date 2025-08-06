package com.noaats.jspark.support.common.presentation;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private String requestDetails;
    private int status;
    private String message;
    private T data;

    public ApiResponse(String requestDetails, int status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.requestDetails = requestDetails;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
