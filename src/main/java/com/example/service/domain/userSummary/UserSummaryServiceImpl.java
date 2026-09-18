package com.example.service.domain.userSummary;

import com.example.service.domain.dailyImage.DailyImage;
import com.example.service.domain.s3.S3Serivce;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserSummaryServiceImpl implements UserSummaryService {
    private final UserSummaryRepository userSummaryRepository;
    private final S3Serivce s3Serivce;

    @Override
    public UserSummary getUserSummary(String phoneNumber) {
        return userSummaryRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional
    public UserSummary updateUserProfile(UserSummaryUpdateRequestDTO requestDTO, String userId)  {
        UserSummary userSummary = userSummaryRepository.findById(userId).orElseThrow();
        if (requestDTO.getProfileImage() != null && !requestDTO.getProfileImage().isEmpty()) {
            String url = s3Serivce.upload(requestDTO.getProfileImage(), "profiles");
            userSummary.setProfileImageUrl(url);
        }
        userSummary.setNickname(requestDTO.getNickname());
        userSummary.setDescription(requestDTO.getDescription());
       return userSummaryRepository.save(userSummary);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return  userSummaryRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public void updatePushToken(String userId, String pushToken) {
        UserSummary userSummary = userSummaryRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        userSummary.setPushToken(pushToken);
    }
}
