package com.example.service.domain.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(String userId);
    Long countByUserIdAndIsReadFalse(String userId);
}
