package com.example.service.domain.dailyApplicaion;

import com.example.service.domain.daily.Daily;
import com.example.service.domain.daily.DailyCreateRequestDTO;
import com.example.service.domain.daily.DailyRepository;
import com.example.service.domain.daily.DailyService;
import com.example.service.domain.dailyImage.DailyImage;
import com.example.service.domain.dailyImage.DailyImageCreateRequestDTO;
import com.example.service.domain.dailyImage.DailyImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyApplicationServiceImpl implements DailyApplicationService {

    private final DailyService dailyService;
    private final DailyImageService dailyImageService;
    private final DailyRepository dailyRepository;

    @Transactional
    @Override
    public void createDaily(DailyCreateRequestDTO requestDTO)  throws IOException {

        Daily daily = dailyService.createDaily(requestDTO);
        DailyImageCreateRequestDTO createRequestDTO = new DailyImageCreateRequestDTO();
        createRequestDTO.setDailyId(daily.getId());
        createRequestDTO.setUrl(requestDTO.getImage());
        dailyImageService.createDailyImage(createRequestDTO);
    }

    @Override
    public List<DailyResponseDTO> getDailyList(String userId) {
        return dailyRepository.findDailyListByUserId(userId)
                .stream()
                .map(p -> DailyResponseDTO.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .imageUrl(p.getImageUrl())
                        .createdAt(p.getCreatedAt())
                        .commentCount(p.getCommentCount())
                        .likeCount(p.getLikeCount())
                        .build())
                .toList();
    }

    @Override
    public DailyResponseDTO getDailyDetail(Long dailyId) {
        return dailyRepository.findDailyById(dailyId)
                .map(p -> DailyResponseDTO.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .imageUrl(p.getImageUrl())
                        .createdAt(p.getCreatedAt())
                        .build())
                .orElseThrow(() -> new RuntimeException("일상을 찾을 수 없습니다."));
    }
}
