package com.example.service.domain.dailyImage;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface DailyImageService {
    List<DailyImage> createDailyImage(Long dailyId, List<MultipartFile> image);
    DailyImage getDailyImage(Long dailyId);
    void saveDailyImage(DailyImage dailyImage);
}