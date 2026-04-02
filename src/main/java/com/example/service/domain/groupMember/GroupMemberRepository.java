package com.example.service.domain.groupMember;

import com.example.service.domain.group.GroupMember;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findByUserId(String userId);
    int countByGroupId(Long groupId);
    List<GroupMember> findByGroupId(Long groupId);

    @Query("""
    SELECT new com.example.service.domain.groupMember.GroupMemberResponse(
        gm.id,
        gm.groupId,
        gm.userId,
        gm.role,
        gm.status,
        us.profileImageUrl,
        us.nickname,
        us.description
    )
    FROM GroupMember gm
    JOIN UserSummary us ON gm.userId = us.userId
    WHERE gm.groupId = :groupId
""")
    List<GroupMemberResponse> findMembersWithName(@Param("groupId") Long groupId);
}
