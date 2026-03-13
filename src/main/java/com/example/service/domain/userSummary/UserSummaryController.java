package com.example.service.domain.userSummary;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSummaryController {

    private final UserSummaryRepository userSummaryRepository;

    @GetMapping("/api/my-info")
    public ResponseEntity<?> getMyInfo(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");

        UserSummary userSummary = userSummaryRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found"));

        return ResponseEntity.ok(userSummary);
    }
}
