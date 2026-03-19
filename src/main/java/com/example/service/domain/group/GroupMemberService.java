package com.example.service.domain.group;

import java.util.List;

public interface GroupMemberService {

    void createGroupMember(GroupMemberCreateRequestDTO requestDTO);

    List<Integer> getGroupMembers(String userId);
}
