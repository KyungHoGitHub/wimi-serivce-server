package com.example.service.domain.daily;

import com.example.service.domain.daily.dto.DailyCreateRequestDTO;
import com.example.service.domain.daily.dto.DailyUpdateRequestDTO;

public interface DailyService {

    Daily createDaily(DailyCreateRequestDTO requestDTO,String userId);

    Daily updateDaily(Long dailyId, String userId, DailyUpdateRequestDTO requestDTO);
}
