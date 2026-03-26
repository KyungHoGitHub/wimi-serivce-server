package com.example.service.domain.notification;

import java.util.List;

public interface NotificationService {

    Notification save(Notification notification);

    List<Notification> getNotifications(String userId);

    Long getUnreadCount(String userId);
}
