package com.example.service.domain.meetingParticipant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MeetingParticipantCreateRequestDTO {
    private Long meetingId;
    private String userId;
    private String status;
}
