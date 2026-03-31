package com.example.service.domain.dailyComment;

public interface DailyCommentProjection {
    Long getId();
    String getUserId();
    String getNickName();
    String getProfileImageUrl();
    String getContent();
    Long getParentId();
    java.time.LocalDateTime getCreatedAt();
}