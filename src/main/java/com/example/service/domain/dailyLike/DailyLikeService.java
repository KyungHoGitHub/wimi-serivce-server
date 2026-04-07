package com.example.service.domain.dailyLike;

public interface DailyLikeService {
    Long getDailyLikeCount(Long dailyId);
    DailyLike createDailyLike(Long dailyId, String userId);
}
