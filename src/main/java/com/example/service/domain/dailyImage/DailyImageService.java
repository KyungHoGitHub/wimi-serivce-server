package com.example.service.domain.dailyImage;


import org.springframework.web.multipart.MultipartFile;


public interface DailyImageService {
    DailyImage createDailyImage(Long dailyId, MultipartFile image);
    DailyImage getDailyImage(Long dailyId);
    void saveDailyImage(DailyImage dailyImage);
}