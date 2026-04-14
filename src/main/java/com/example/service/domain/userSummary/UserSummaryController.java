package com.example.service.domain.userSummary;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserSummaryController {
    @Value("${server.url}")
    private String serverUrl;

    @Value("${file.upload-dir}")
    private String uploadDir;
    private final UserSummaryRepository userSummaryRepository;
    private final UserSummaryService userSummaryService;

    @GetMapping("/api/my-info")
    public ResponseEntity<?> getMyInfo(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");

        UserSummary userSummary = userSummaryRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        return ResponseEntity.ok(userSummary);
    }

    @PatchMapping(value = "/api/user/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProfileImage( @AuthenticationPrincipal String userId, @ModelAttribute UserSummaryUpdateRequestDTO requestDTO) throws IOException {

        System.out.println("nickname: " + requestDTO.getNickname());
        System.out.println("description: " + requestDTO.getDescription());
        System.out.println("profileImage: " + requestDTO.getProfileImage());
        return ResponseEntity.ok(userSummaryService.updateUserProfile(requestDTO, userId));
    }

    @GetMapping("/api/user-summary/{phoneNumber}")
    public ResponseEntity<UserSummary> getUserSummary(@PathVariable("phoneNumber") String phoneNumber) {
        return ResponseEntity.ok(userSummaryService.getUserSummary(phoneNumber));
    }
}
