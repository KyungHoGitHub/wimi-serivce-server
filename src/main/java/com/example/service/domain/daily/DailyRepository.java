package com.example.service.domain.daily;


import com.example.service.domain.dailyApplicaion.DailyListProjection;
import com.example.service.domain.dailyApplicaion.DailyResponseDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface DailyRepository extends JpaRepository<Daily, Long> {
    @Query(value = """
            SELECT 
                d.id,
                d.content,
                d.created_user_id AS createdUserId,
                d.created_at AS createdAt,
                di.url AS imageUrl,
                COUNT(DISTINCT dc.id) AS commentCount,
                COUNT(DISTINCT dl.id) AS likeCount
            FROM daily d
            LEFT JOIN daily_image di ON d.id = di.daily_id
            LEFT JOIN daily_comment dc ON d.id = dc.daily_id
            LEFT JOIN daily_like dl ON d.id = dl.daily_id
            WHERE d.group_id IN (
                SELECT gm.group_id 
                FROM group_member gm 
                WHERE gm.user_id = :userId
            )
            GROUP BY d.id, d.content, d.created_user_id, d.created_at, di.url
            ORDER BY d.created_at DESC
            """, nativeQuery = true)
    List<DailyListProjection> findDailyListByUserId(@Param("userId") String userId);

    @Query(value = """
            SELECT 
                d.id,
                d.title,
                g.name,            
                d.content,
                d.created_user_id AS createdUserId,
                d.created_at AS createdAt,
                di.url AS imageUrl
            FROM daily d
            LEFT JOIN daily_image di ON d.id = di.daily_id
            INNER JOIN groups g ON d.group_id = g.id
            WHERE d.id = :dailyId
            """, nativeQuery = true)
    Optional<DailyListProjection> findDailyById(@Param("dailyId") Long dailyId);
}
