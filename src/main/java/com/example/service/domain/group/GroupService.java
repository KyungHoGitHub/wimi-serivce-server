package com.example.service.domain.group;

import java.util.List;

public interface GroupService {
    Group createGroup(GroupCreateRequestDTO requestDTO);

    List<GroupListResponseDTO> getGroups(String userId);

    Group getGroupDetail(Long groupId);

    void deleteGroup(Long groupId,String userId);
}
