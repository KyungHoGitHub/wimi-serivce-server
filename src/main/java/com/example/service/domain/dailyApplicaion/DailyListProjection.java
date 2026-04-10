package com.example.service.domain.dailyApplicaion;

public interface DailyListProjection {
    Long getId();
    String getTitle();
    String getContent();
    String getCreatedUserId();
    String getImageUrl();
    java.time.LocalDateTime getCreatedAt();
    Long getCommentCount();  // 추가
    Long getLikeCount();
}