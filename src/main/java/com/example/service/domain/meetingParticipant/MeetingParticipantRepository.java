package com.example.service.domain.meetingParticipant;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, Long> {
    @Query(value = """
        SELECT 
            mp.meeting_id AS meetingId,
            mp.status AS status,
            u.user_id AS userId,
            u.nickname AS nickname,
            u.profile_image_url AS profileImageUrl
        FROM meeting_participant mp
        LEFT JOIN user_summary u ON mp.user_id = u.user_id
        WHERE mp.meeting_id IN (:meetingIds)
        """, nativeQuery = true)
    List<MeetingParticipantProjection> findParticipantsByMeetingIds(
            @Param("meetingIds") List<Long> meetingIds);
}
