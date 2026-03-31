package com.example.service.domain.dailyApplicaion;

import com.example.service.domain.daily.DailyCreateRequestDTO;

import java.io.IOException;
import java.util.List;

public interface DailyApplicationService {
    void createDaily(DailyCreateRequestDTO requestDTO) throws IOException;

    List<DailyResponseDTO> getDailyList(String userId);

    DailyResponseDTO getDailyDetail(Long dailyId);
}
