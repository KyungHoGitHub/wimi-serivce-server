package com.example.service.domain.userSummary;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @PostMapping("api/user/profile-image")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal String userId) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path savePath = Paths.get(uploadDir, fileName);

        Files.createDirectories(savePath.getParent());
        file.transferTo(savePath.toFile());

        String imageUrl = serverUrl + "/images/" + fileName;

        return ResponseEntity.ok(userSummaryService.updateUserProfile(imageUrl, userId));
    }

    @GetMapping("/api/user-summary/{phoneNumber}")
    public ResponseEntity<UserSummary> getUserSummary(@PathVariable("phoneNumber") String phoneNumber) {
        return ResponseEntity.ok(userSummaryService.getUserSummary(phoneNumber));
    }
}
