package com.example.service.domain.sms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsVerifyRequestDTO {
    private String phoneNumber;
    private String code;
}
