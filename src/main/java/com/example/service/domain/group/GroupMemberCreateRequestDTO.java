package com.example.service.domain.group;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GroupMemberCreateRequestDTO {
    private int groupId;
    private String userId;
    private Role role;
    private Status status;
}
