package com.example.service.domain.dailyApplicaion;

import com.example.service.domain.daily.Daily;
import com.example.service.domain.daily.dto.DailyCreateRequestDTO;
import com.example.service.domain.daily.DailyRepository;
import com.example.service.domain.daily.DailyService;
import com.example.service.domain.daily.dto.DailyCreateResponse;
import com.example.service.domain.dailyImage.DailyImageService;
import com.example.service.domain.log.ActivityLog;
import com.example.service.domain.log.ActivityLogService;
import com.example.service.domain.log.LogAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;      // ✅ Spring Data
import org.springframework.data.domain.Pageable;  // ✅ Spring Data
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyApplicationServiceImpl implements DailyApplicationService {

    private final DailyService dailyService;
    private final DailyImageService dailyImageService;
    private final DailyRepository dailyRepository;
    private final ActivityLogService activityLogService;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public DailyCreateResponse createDaily(DailyCreateRequestDTO requestDTO, String userId) {

        log.info("일상 생성 시작 - userId: {}, groupId: {}", userId, requestDTO.getGroupId());

        try {
            Daily daily = dailyService.createDaily(requestDTO, userId);
            log.debug("일상 저장 완료 - dailyId: {}", daily.getId());

            // 사용자가 이미지를 올린 경우
            if (requestDTO.getImages() != null) {
                log.debug("일상 이미지 업로드 시작 - dailyId: {}", daily.getId());
                dailyImageService.createDailyImage(daily.getId(), requestDTO.getImages());
                log.debug("일상 이미지 업로드 완료 - dailyId: {}", daily.getId());
            }

            activityLogService.saveActivityLog(ActivityLog.builder()
                    .userId(userId)
                    .action(LogAction.DAILY_CREATE.name())
                    .targetId(daily.getId())
                    .status("SUCCESS")
                    .build());

            log.info("일상 생성 완료 - dailyId: {}", daily.getId());
            return DailyCreateResponse.from(daily);

        } catch (Exception e) {
            activityLogService.saveActivityLog(ActivityLog.builder()
                    .userId(userId)
                    .action(LogAction.DAILY_CREATE.name())
                    .status("FAIL")
                    .errorMsg(e.getMessage())
                    .build());
            log.error("일상 생성 실패 - userId: {}", userId, e);
            throw e;
        }
    }

    @Override
    public Page<DailyResponseDTO> getDailyList(String userId,Long groupId, Pageable pageable) {
        return dailyRepository.findDailyListByUserId(userId, groupId,pageable)
                .map(p -> DailyResponseDTO.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .thumbnailUrl(p.getThumbnailUrl())
                        .createdAt(p.getCreatedAt())
                        .commentCount(p.getCommentCount())
                        .likeCount(p.getLikeCount())
                        .authorNickname(p.getAuthorNickname())
                        .authorImageUrl(p.getAuthorImageUrl())
                        .build());
    }

    @Override
    public DailyResponseDTO getDailyDetail(Long dailyId, String userId) {
        return dailyRepository.findDailyById(dailyId)
                .map(p -> DailyResponseDTO.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .name(p.getName())
                        .content(p.getContent())
                        .images(parseImages(p.getImages()))
                        .createdAt(p.getCreatedAt())
                        .isOwner(userId.equals(p.getCreatedUserId()))
                        .build())
                .orElseThrow(() -> new RuntimeException("일상을 찾을 수 없습니다."));
    }

    private List<DailyResponseDTO.ImageDTO> parseImages(String json) {
        if (json == null) return List.of();
        try {
            return objectMapper.readValue(
                    json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, DailyResponseDTO.ImageDTO.class)
            );
        } catch (JsonProcessingException e) {
            return List.of();
        }
    }
}
