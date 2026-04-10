package com.example.service.domain.userSummary;

import com.example.service.domain.dailyImage.DailyImage;
import com.example.service.domain.s3.S3Serivce;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserSummaryServiceImpl implements UserSummaryService {
    private final UserSummaryRepository userSummaryRepository;
    private final S3Serivce s3Serivce;

    @Override
    public UserSummary getUserSummary(String phoneNumber) {
        String formatted = phoneNumber.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
        return userSummaryRepository.findByPhoneNumber(formatted);
    }

    @Override
    @Transactional
    public UserSummary updateUserProfile(String imageUrl, String userId) {
        userSummaryRepository.updateProfileImage(imageUrl, userId);
        return userSummaryRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
    }
}
