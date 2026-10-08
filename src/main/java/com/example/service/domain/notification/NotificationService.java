package com.example.service.domain.notification;

import java.util.List;

public interface NotificationService {

    Notification save(Notification notification);

    List<Notification> getNotifications(String userId);

    Long getUnreadCount(String userId);

    void readNotification(Long notificationId);

    void markAsReadByReference(String userId, Type type, Long referenceId);
}
