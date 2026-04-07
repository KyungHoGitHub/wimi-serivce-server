package com.example.service.domain.dailyLike;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DailyLikeController {
    private final DailyLikeService dailyLikeService;

    @GetMapping("/api/daily/{id}/like")
    public ResponseEntity<?> getDailyLikes(@PathVariable Long id, @AuthenticationPrincipal String userId){
        Long likeCount  = dailyLikeService.getDailyLikeCount(id);
        return ResponseEntity.ok(likeCount);
    }

    @PostMapping("/api/daily/{id}/like")
    public ResponseEntity<?> createDailyLike(@PathVariable Long id, @AuthenticationPrincipal String userId){

        return ResponseEntity.ok(dailyLikeService.createDailyLike(id,userId));
    }
}
