package com.example.service.domain.group;

import com.example.service.domain.groupMember.GroupMemberRepository;
import com.example.service.domain.groupMember.GroupMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupMemberService groupMemberService;
    private final GroupRepository groupRepository;
    private  final GroupMemberRepository groupMemberRepository;
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
    public List<GroupListResponseDTO> getGroups(String userId) {

        List<Long> groupIds = groupMemberService.getGroupMembers(userId);
        List<Group> groups = groupRepository.findByIdIn(groupIds);
        return groups.stream()
                .map(group -> GroupListResponseDTO.builder()
                        .id(group.getId())
                        .name(group.getName())
                        .description(group.getDescription())
                        .category(group.getCategory())
                        .profileImageUrl(group.getProfileImageUrl())
                        .createdBy(group.getCreatedBy())
                        .createdAt(group.getCreatedAt())
                        .memberCount(groupMemberRepository.countByGroupId(group.getId())) // 추가
                        .build()
                )
                .collect(Collectors.toList());

    }

    @Override
    public Group getGroupDetail(Long groupId) {
        Optional<Group> group = groupRepository.findById(groupId).or(()-> Optional.empty());

        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: " + groupId));
    }

    @Transactional
    @Override
    public void deleteGroup(Long groupId, String userId) {
        groupRepository.deleteByIdAndCreatedBy(groupId,userId);
    }
}

