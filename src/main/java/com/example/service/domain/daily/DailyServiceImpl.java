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
import java.util.ArrayList;
import java.util.List;

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
        Daily daily = dailyRepository.findById(dailyId).orElseThrow(() -> new RuntimeException("Daily not found: " + dailyId));

        daily.setContent(requestDTO.getContent());
        daily.setUpdatedAt(LocalDateTime.now());
        dailyRepository.save(daily);

        // 이미지 요청 데이터가 있을때만
        if (requestDTO.getImages() != null) {

            // 기존 일상 이미지 리스트 조회
            List<DailyImage> exixtingDailyImageList = dailyImageService.getDailyImageList(daily.getId());

            // 새로운 일상 이미지 리스트 값 선언
            List<DailyImage> newDailyImageList = new ArrayList<>();
            for (int i = 0; i < requestDTO.getImages().size(); i++) {
                String newUrl = s3Serivce.upload(requestDTO.getImages().get(i), "profiles");
                newDailyImageList.add(DailyImage.builder()
                        .dailyId(daily.getId())
                        .url(newUrl)
                        .orderIndex((long) i)
                        .build());
            }

            exixtingDailyImageList.forEach(image -> s3Serivce.delete(image.getUrl()));
            dailyImageService.deleteAllByDailyId(daily.getId());
            dailyImageService.saveDailyImages(newDailyImageList);
        }

        return daily;
    }

}




