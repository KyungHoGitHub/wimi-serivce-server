package com.example.service.domain.groupMember;

import com.example.service.domain.group.Role;
import com.example.service.domain.group.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupMemberResponse {
    private Long groupMemberId;
    private Long groupId;
    private String userId;
    private Role role;
    private Status status;
    private String profileImageUrl;
    private String nickname;
    private String description;

}
