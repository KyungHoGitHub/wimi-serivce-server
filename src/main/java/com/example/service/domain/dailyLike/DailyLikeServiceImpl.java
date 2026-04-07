package com.example.service.domain.dailyLike;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyLikeServiceImpl implements DailyLikeService{
    private final DailyLikeRepository dailyLikeRepository;


    @Override
    public Long getDailyLikeCount(Long dailyId) {
        Long dailyLikeCount = dailyLikeRepository.countByDailyId(dailyId);
        return dailyLikeCount;
    }

    @Override
    public DailyLike createDailyLike(Long dailyId, String userId) {


        DailyLike dailyLike = DailyLike.builder()
                .dailyId(dailyId)
                .userId(userId)
                .build();
        return dailyLikeRepository.save(dailyLike);
    }
}
