package com.example.service.domain.userSummary;

import org.springframework.web.multipart.MultipartFile;

public interface UserSummaryService {
    UserSummary getUserSummary(String phoneNumber);
    UserSummary updateUserProfile(String imageUrl, String userId);
}
