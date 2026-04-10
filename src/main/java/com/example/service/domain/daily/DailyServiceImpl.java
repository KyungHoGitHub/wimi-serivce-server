package com.example.service.domain.daily;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyServiceImpl implements DailyService {
    private final DailyRepository dailyRepository;


    @Override
    public Daily createDaily(DailyCreateRequestDTO requestDTO) {

        Daily daily = Daily.builder()
                .groupId(requestDTO.getGroupId())
                .createdUserId(requestDTO.getCreateUserId())
                .content(requestDTO.getContent())
                .scope(requestDTO.getScope())
                .build();

        return dailyRepository.save(daily);

    }
}
