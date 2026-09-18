package com.example.service.common.exception;

public class NotRegisteredPhoneException extends RuntimeException{
    public NotRegisteredPhoneException(String phoneNumber) {
        super("등록되지 않은 전화번호 입니다."+ phoneNumber);
    }
}
