package com.example.service.domain.dailyImage;

import com.example.service.domain.s3.S3Serivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
    public List<DailyImage> createDailyImage(Long dailyId, List<MultipartFile> image) {
        List<DailyImage> dailyImages = new ArrayList<>();

        for(int i = 0; i < image.size(); i++) {
            String url = s3Serivce.upload(image.get(i), "profiles");
            DailyImage dailyImage = DailyImage.builder()
                    .dailyId(dailyId)
                    .url(url)
                    .orderIndex((long) i)
                    .build();
            dailyImages.add(dailyImage);
        }
        return dailyImageRepository.saveAll(dailyImages);
    }

    @Override
    public DailyImage getDailyImage(Long dailyId) {
        return dailyImageRepository.findByDailyId(dailyId);
    }

    @Override
    public void saveDailyImage(DailyImage dailyImage) {
        dailyImageRepository.save(dailyImage);
    }


}
