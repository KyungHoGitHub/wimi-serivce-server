package com.example.service.common.exception;

public class AlreadyRegisteredPhoneException extends RuntimeException {
    public AlreadyRegisteredPhoneException(String phoneNumber) {
        super("이미 가입된 전화번호입니다: " + phoneNumber);
    }
}

