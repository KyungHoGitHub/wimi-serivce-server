package com.example.service.domain.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/api/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@AuthenticationPrincipal String userId){
         return ResponseEntity.ok(notificationService.getNotifications(userId));
    }

    @GetMapping("/api/notifications/unReadCount")
    public ResponseEntity<?> getUnReadCount(@AuthenticationPrincipal String userId){
        return ResponseEntity.ok(notificationService.getUnreadCount(userId));
    }
}
