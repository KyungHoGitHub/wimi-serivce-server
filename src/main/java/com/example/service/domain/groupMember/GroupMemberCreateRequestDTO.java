package com.example.service.domain.groupMember;

import com.example.service.domain.group.Role;
import com.example.service.domain.group.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GroupMemberCreateRequestDTO {
    private Long groupId;
    private String userId;
    private Role role;
    private Status status;
}
