package com.example.service.domain.dailyComment;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyCommentRepository extends JpaRepository<DailyComment, Long> {
    @Query(value = """
    SELECT 
        dc.id,
        dc.user_id AS userId,
        COALESCE(us.nickname, dc.user_id) AS nickName,
        us.profile_image_url AS profileImageUrl,
        dc.content,
        dc.parent_id AS parentId,
        dc.created_at AS createdAt
    FROM daily_comment dc
    LEFT JOIN user_summary us ON dc.user_id = us.user_id
    WHERE dc.daily_id = :dailyId
    ORDER BY dc.created_at ASC
    """, nativeQuery = true)
    List<DailyCommentProjection> findCommentsByDailyId(@Param("dailyId") Long dailyId);
}
