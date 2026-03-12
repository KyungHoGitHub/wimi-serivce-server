package com.example.service.domain.kafka;

import com.example.service.domain.userSummary.UserSummary;
import com.example.service.domain.userSummary.UserSummaryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventConsumer {

    private final UserSummaryRepository userSummaryRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "user.registered", groupId = "service-server")
    public void handleUserRegistered(String message) {
        try {
            UserRegisteredEvent event = objectMapper.readValue(message, UserRegisteredEvent.class);
            log.info("유저 등록 이벤트 수신: {}", event.getNickname());

            UserSummary userSummary = UserSummary.builder()
                    .userId(event.getUserId())
                    .nickname(event.getNickname())
                    .phoneNumber(event.getPhoneNumber())
                    .build();

            userSummaryRepository.save(userSummary);
            log.info("user_summary 저장 완료: {}", event.getNickname());

        } catch (Exception e) {
            log.error("유저 등록 이벤트 처리 실패: {}", e.getMessage());
        }
    }
}
