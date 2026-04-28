package com.example.service.domain.daily;


import com.example.service.domain.daily.dto.DailyCreateRequestDTO;
import com.example.service.domain.daily.dto.DailyUpdateRequestDTO;
import com.example.service.domain.dailyImage.DailyImage;
import com.example.service.domain.dailyImage.DailyImageService;
import com.example.service.domain.s3.S3Serivce;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyServiceImpl implements DailyService {
    private final DailyRepository dailyRepository;
    private final DailyImageService dailyImageService;
    private final S3Serivce s3Serivce;

    @Override
    public Daily createDaily(DailyCreateRequestDTO requestDTO, String userId) {

        Daily daily = Daily.builder()
                .groupId(requestDTO.getGroupId())
                .createdUserId(userId)
                .content(requestDTO.getContent())
                .scope(requestDTO.getScope())
                .build();

        return dailyRepository.save(daily);

    }

    @Transactional
    @Override
    public Daily updateDaily(Long dailyId, String userId, DailyUpdateRequestDTO requestDTO) {
        // 1. daily 컨텐츠 수정
        Daily daily = dailyRepository.findById(dailyId).orElseThrow(()-> new RuntimeException("Daily not found: " + dailyId));

        daily.setContent(requestDTO.getContent());
        daily.setUpdatedAt(LocalDateTime.now());
        dailyRepository.save(daily);
        if (requestDTO.getImage() != null) {
            DailyImage dailyImage = dailyImageService.getDailyImage(daily.getId());
            if (dailyImage != null) {
                    s3Serivce.delete(dailyImage.getUrl());
                    String newUrl = s3Serivce.upload(requestDTO.getImage(), "profiles");
                    dailyImage.setUrl(newUrl);
                    dailyImageService.saveDailyImage(dailyImage);
            }
        }

        return null;
    }
}
