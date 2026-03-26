package com.example.service.domain.groupApplicaion;

import com.example.service.domain.group.*;
import com.example.service.domain.groupMember.GroupMemberCreateRequestDTO;
import com.example.service.domain.groupMember.GroupMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupApplicationService {

    private final GroupService groupService;
    private final GroupMemberService groupMemberService;

    public void createGroup(GroupCreateRequestDTO requestDTO) {

        // 1 group 생성
        Group group = groupService.createGroup(requestDTO);

        // 2 groupMember 생성
        groupMemberService.createGroupMember(GroupMemberCreateRequestDTO.builder()
                .groupId(group.getId())
                .userId(group.getCreatedBy())
                .role(Role.ADMIN)
                .status(Status.IS_ABLED)
                .build());
    }
}
