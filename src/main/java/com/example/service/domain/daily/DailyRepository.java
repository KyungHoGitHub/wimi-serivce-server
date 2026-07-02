package com.example.service.domain.daily;


import com.example.service.domain.dailyApplicaion.DailyListProjection;
import com.example.service.domain.dailyApplicaion.DailyResponseDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;      // ✅ Spring Data
import org.springframework.data.domain.Pageable;  // ✅ Spring Data
import java.util.List;
import java.util.Optional;


public interface DailyRepository extends JpaRepository<Daily, Long> {
    @Query(value = """
    SELECT 
        d.id,
        d.content,
        d.created_user_id AS createdUserId,
        d.created_at AS createdAt,
        di.url AS thumbnailUrl,
        COUNT(DISTINCT dc.id) AS commentCount,
        COUNT(DISTINCT dl.id) AS likeCount,
        us.nickname AS authorNickname,
        us.profile_image_url AS authorImageUrl
    FROM daily d
    LEFT JOIN LATERAL (
        SELECT url FROM daily_image
        WHERE daily_id = d.id
        ORDER BY order_index ASC
        LIMIT 1
    ) di ON true
    LEFT JOIN daily_comment dc ON d.id = dc.daily_id
    LEFT JOIN daily_like dl ON d.id = dl.daily_id
    LEFT JOIN user_summary us ON d.created_user_id = us.user_id
    WHERE d.group_id IN (
        SELECT gm.group_id 
        FROM group_member gm 
        WHERE gm.user_id = :userId
    )
    GROUP BY d.id, d.content, d.created_user_id, d.created_at, di.url, us.nickname, us.profile_image_url
    ORDER BY d.created_at DESC
    """,
            countQuery = """
    SELECT COUNT(DISTINCT d.id)
    FROM daily d
    WHERE d.group_id IN (
        SELECT gm.group_id 
        FROM group_member gm 
        WHERE gm.user_id = :userId
    )
    """,
            nativeQuery = true)
    Page<DailyListProjection> findDailyListByUserId(@Param("userId") String userId, Pageable pageable);

    @Query(value = """
            SELECT
                d.id,
                d.title,
                g.name,           
                d.content,
                d.created_user_id AS createdUserId,
                d.created_at AS createdAt,
                JSON_AGG(
                    JSON_BUILD_OBJECT(
                        'url', di.url,
                        'orderIndex', di.order_index
                    ) ORDER BY di.order_index
                ) FILTER (WHERE di.id IS NOT NULL) AS images
            FROM daily d
            LEFT JOIN daily_image di ON d.id = di.daily_id
            INNER JOIN groups g ON d.group_id = g.id
            WHERE d.id = :dailyId
            GROUP BY d.id, d.title, g.name, d.content, d.created_user_id, d.created_at
            """, nativeQuery = true)
    Optional<DailyListProjection> findDailyById(@Param("dailyId") Long dailyId);
}
