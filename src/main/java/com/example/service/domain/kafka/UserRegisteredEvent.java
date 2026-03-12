package com.example.service.domain.kafka;

import lombok.Data;

@Data
public class UserRegisteredEvent {
    private String userId;
    private String nickname;
    private String phoneNumber;
}
