package com.example.service.domain.meeting;

import com.example.service.domain.dailyApplicaion.DailyListProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query(value = """
    SELECT 
        m.id AS id,
        m.title AS title,
        m.content AS content,
        m.scope AS scope,
        m.group_id AS groupId,
        m.start_at AS startAt,
        m.end_at AS endAt,
        mp.status AS myStatus,
        
        -- 생성자 정보
        creator.user_id AS createdBy,
        creator.nickname AS creatorNickname,
        creator.profile_image_url AS creatorProfileUrl,
        
        -- 참여자 수
        (SELECT COUNT(*) FROM meeting_participant WHERE meeting_id = m.id) AS participantCount
        
    FROM meeting m
    LEFT JOIN meeting_participant mp 
        ON m.id = mp.meeting_id 
        AND mp.user_id = :userId
    LEFT JOIN user_summary creator
        ON m.created_user_id = creator.user_id
    WHERE m.group_id IN (
        SELECT gm.group_id 
        FROM group_member gm 
        WHERE gm.user_id = :userId
    )
    ORDER BY m.start_at DESC
    """, nativeQuery = true)
    List<MeetingListProjection> findMeetingListByUserId(@Param("userId") String userId);
}
