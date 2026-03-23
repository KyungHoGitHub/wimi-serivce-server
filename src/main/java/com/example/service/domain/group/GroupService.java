package com.example.service.domain.group;

import java.util.List;

public interface GroupService {
    Group createGroup(GroupCreateRequestDTO requestDTO);

    List<Group> getGroups(String userId);

    Group getGroupDetail(int groupId);
}
