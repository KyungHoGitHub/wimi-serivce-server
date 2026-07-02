package com.example.service.domain.dailyApplicaion;

import com.example.service.domain.daily.dto.DailyCreateRequestDTO;
import com.example.service.domain.daily.dto.DailyCreateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface DailyApplicationService {
    DailyCreateResponse createDaily(DailyCreateRequestDTO requestDTO, String userId);

    Page<DailyResponseDTO> getDailyList(String userId, Pageable pageable);

    DailyResponseDTO getDailyDetail(Long dailyId, String userId);
}
