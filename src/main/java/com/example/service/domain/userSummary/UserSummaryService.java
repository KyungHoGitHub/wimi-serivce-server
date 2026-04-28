package com.example.service.domain.userSummary;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserSummaryService {
    UserSummary getUserSummary(String phoneNumber);
    UserSummary updateUserProfile(UserSummaryUpdateRequestDTO imageUrl, String userId);
}
