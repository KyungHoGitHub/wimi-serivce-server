package com.example.service.domain.meetingParticipant;

public interface MeetingParticipantProjection {
    Long getMeetingId();
    String getStatus();
    String getUserId();
    String getNickname();
    String getProfileImageUrl();
}
