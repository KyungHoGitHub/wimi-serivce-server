package com.example.service.domain.dailyImage;

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

    @Override
    public DailyImage createDailyImage(DailyImageCreateRequestDTO requestDTO) throws IOException {
        String fileName = UUID.randomUUID() + "_" + requestDTO.getUrl().getOriginalFilename();
        Path savePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(savePath.getParent());
        requestDTO.getUrl().transferTo(savePath.toFile());

        String imageUrl = serverUrl + "/images/" + fileName;
        DailyImage dailyImage = DailyImage.builder()
                .dailyId(requestDTO.getDailyId())
                .url(imageUrl)
                .orderIndex(requestDTO.getOrderIndex())
                .build();
        return dailyImageRepository.save(dailyImage);
    }
}
