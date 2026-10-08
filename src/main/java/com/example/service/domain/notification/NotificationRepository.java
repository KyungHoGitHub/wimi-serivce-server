package com.example.service.domain.notification;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(String userId);

    Long countByUserIdAndIsReadFalse(String userId);

    List<Notification> findByUserIdAndIsReadFalseOrderByCreatedAtDesc(String userId);

    @Modifying
    @Query("""
                    UPDATE Notification n
                    SET n.isRead = true
                    WHERE n.userId = :userId
                        AND n.type = :type
                        AND n.referenceId = :referenceId
            """)
    void markAsReadByReference(
            @Param("userId") String userId,
            @Param("type") Type type,
            @Param("referenceId") Long referenceId);

}
