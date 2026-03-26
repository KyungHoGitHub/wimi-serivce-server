package com.example.service.domain.groupInvite;

public interface GroupInviteService {

    void createGroupInvite(GroupInviteCreateRequestDTO requestDTO);

    void acceptGroupInvite(Long inviteId, String userId);
    void rejectGroupInvite(Long inviteId, String userId);
}
