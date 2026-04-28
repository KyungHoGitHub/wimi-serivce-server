package com.example.service.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    DAILY_CREATED(HttpStatus.CREATED,"일상이 성공적으로 생성되었습니다.");

    private final HttpStatus status;
    private final String message;
}
