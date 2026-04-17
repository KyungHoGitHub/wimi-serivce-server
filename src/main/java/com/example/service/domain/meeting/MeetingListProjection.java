package com.example.service.domain.meeting;

import java.time.LocalDateTime;

public interface MeetingListProjection {
    Long getId();
    String getTitle();
    String getContent();
    String getScope();
    Long getGroupId();
    LocalDateTime getStartAt();
    LocalDateTime getEndAt();
    String getCreatedBy();
    String getMyStatus(); // 내 참여 상태 (PENDING / ACCEPTED / REJECTED)

    String getCreatorNickname();
    String getCreatorProfileUrl();

    // 참여자 수
    Integer getParticipantCount();
}
