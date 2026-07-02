package com.example.service.domain.dailyApplicaion;

public interface DailyListProjection {
    Long getId();
    String getTitle();
    String getContent();
    String getName();
    String getCreatedUserId();
    String getImageUrl();
    String getThumbnailUrl();
    java.time.LocalDateTime getCreatedAt();
    Long getCommentCount();  // 추가
    Long getLikeCount();
    String getAuthorNickname();
    String getAuthorImageUrl();
    String getImages();
}