package com.example.service.domain.group;

import com.example.service.domain.groupMember.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupMemberService groupMemberService;
    private final GroupRepository groupRepository;

    @Override
    public Group createGroup(GroupCreateRequestDTO requestDTO) {

        Group group = Group.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .category(requestDTO.getCategory())
                .profileImageUrl(requestDTO.getProfileImageUrl())
                .createdBy(requestDTO.getCreatedBy())
                .build();

        return groupRepository.save(group);
    }

    @Override
    public List<Group> getGroups(String userId) {

        List<Long> groupIds = groupMemberService.getGroupMembers(userId);

        return groupRepository.findByIdIn(groupIds);

    }

    @Override
    public Group getGroupDetail(Long groupId) {
        Optional<Group> group = groupRepository.findById(groupId).or(()-> Optional.empty());

        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: " + groupId));
    }
}

