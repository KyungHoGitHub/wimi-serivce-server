package com.example.service.domain.dailyImage;

import com.example.service.domain.s3.S3Serivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DailyImageServiceImpl implements DailyImageService{

    @Value("${server.url}")
    private String serverUrl;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final DailyImageRepository dailyImageRepository;
    private final S3Serivce s3Serivce;

    @Override
    public DailyImage createDailyImage(DailyImageCreateRequestDTO requestDTO) throws IOException {

        String url = s3Serivce.upload(requestDTO.getUrl(), "profiles");
        DailyImage dailyImage = DailyImage.builder()
                .dailyId(requestDTO.getDailyId())
                .url(url)
                .orderIndex(requestDTO.getOrderIndex())
                .build();
        return dailyImageRepository.save(dailyImage);
    }
}
