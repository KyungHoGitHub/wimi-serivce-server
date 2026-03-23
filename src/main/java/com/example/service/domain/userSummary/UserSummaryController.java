package com.example.service.domain.userSummary;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSummaryController {

    private final UserSummaryRepository userSummaryRepository;
    private final UserSummaryService userSummaryService;
    @GetMapping("/api/my-info")
    public ResponseEntity<?> getMyInfo(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");

        UserSummary userSummary = userSummaryRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found"));

        return ResponseEntity.ok(userSummary);
    }

    @PostMapping("api/user/profile-image")
    public ResponseEntity<?> uploadProfileImage(){
        return null;
    }

    @GetMapping("/api/user-summary/{phoneNumber}")
    public ResponseEntity<UserSummary> getUserSummary(@PathVariable("phoneNumber") String phoneNumber) {
        return ResponseEntity.ok(userSummaryService.getUserSummary(phoneNumber));
    }
}
