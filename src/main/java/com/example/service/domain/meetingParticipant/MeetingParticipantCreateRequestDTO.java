package com.example.service.domain.meetingParticipant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingParticipantCreateRequestDTO {
    private Long meetingId;
    private String userId;
    private String status;
}
