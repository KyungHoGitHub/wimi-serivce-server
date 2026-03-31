package com.example.service.domain.dailyImage;


import java.io.IOException;

public interface DailyImageService {
    DailyImage createDailyImage(DailyImageCreateRequestDTO requestDTO) throws IOException;
}