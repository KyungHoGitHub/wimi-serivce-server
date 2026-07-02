package com.example.service.domain.notification;

import com.example.service.domain.dailyComment.DailyCommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/api/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(notificationService.getNotifications(userId));
    }


    @PatchMapping("/api/notifications/{id}/read")
    public ResponseEntity<?> readNotification(@PathVariable("id") Long id, @AuthenticationPrincipal String userId) {
        notificationService.readNotification(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/api/notifications/unReadCount")

    public ResponseEntity<?> getUnReadCount(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(notificationService.getUnreadCount(userId));
    }
}
