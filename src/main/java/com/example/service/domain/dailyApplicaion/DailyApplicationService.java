package com.example.service.domain.dailyApplicaion;

import com.example.service.domain.daily.dto.DailyCreateRequestDTO;
import com.example.service.domain.daily.dto.DailyCreateResponse;

import java.io.IOException;
import java.util.List;

public interface DailyApplicationService {
    DailyCreateResponse createDaily(DailyCreateRequestDTO requestDTO, String userId);

    List<DailyResponseDTO> getDailyList(String userId);

    DailyResponseDTO getDailyDetail(Long dailyId, String userId);
}
