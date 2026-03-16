package com.example.service.domain.group;

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

        Group group = groupService.createGroup(requestDTO);

        groupMemberService.createGroupMember(GroupMemberCreateRequestDTO.builder()
                .groupId(group.getId())
                .userId(group.getCreatedBy())
                .role(Role.ADMIN)
                .status(Status.IS_ABLED)
                .build());
    }
}
