package com.example.service.domain.meetingApplication;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeetingApplicationCreateRequestDTO {
    String title;
    String content;
    LocalDateTime startAt;
    LocalDateTime endAt;
    Long groupId;
    String meetingParticipantId;
}
