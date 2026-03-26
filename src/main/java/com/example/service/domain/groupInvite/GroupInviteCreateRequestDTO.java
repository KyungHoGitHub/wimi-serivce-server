package com.example.service.domain.groupInvite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupInviteCreateRequestDTO {

    private String invitedUserId;
    private String invitedBy;
    private String status;
    private Long groupId;
}
