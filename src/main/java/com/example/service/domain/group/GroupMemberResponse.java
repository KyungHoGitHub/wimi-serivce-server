package com.example.service.domain.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupMemberResponse {
    private Integer groupMemberId;
    private Integer groupId;
    private String userId;
    private Role role;
    private Status status;
    private String profileImageUrl;
    private String nickname;
    private String description;

}
