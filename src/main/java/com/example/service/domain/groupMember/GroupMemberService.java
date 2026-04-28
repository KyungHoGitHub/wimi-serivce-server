package com.example.service.domain.groupMember;

import com.example.service.domain.group.GroupMember;

import java.util.List;

public interface GroupMemberService {

    void createGroupMember(GroupMemberCreateRequestDTO requestDTO);

    List<Long> getGroupMembers(String userId);

    List<GroupMemberResponse> getGroupMemberList(Long groupId);

    GroupMember save(GroupMember groupMember);

    void deleteGroupMember(Long groupId,String userId);
}
