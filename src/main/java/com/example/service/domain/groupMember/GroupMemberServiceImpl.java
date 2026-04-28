package com.example.service.domain.groupMember;

import com.example.service.domain.group.GroupMember;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {
    private final GroupMemberRepository groupMemberRepository;


    @Override
    public void createGroupMember(GroupMemberCreateRequestDTO requestDTO) {
        GroupMember groupMember =GroupMember.builder()
                .groupId(requestDTO.getGroupId())
                .userId(requestDTO.getUserId())
                .role(requestDTO.getRole())
                .status(requestDTO.getStatus())
                .build();

        groupMemberRepository.save(groupMember);
    }

    @Override
    public List<Long> getGroupMembers(String userId) {
        List<Long> groupIds = groupMemberRepository.findByUserId(userId)
                .stream()
                .map(GroupMember::getGroupId)
                .toList();


        return groupIds;
    }

    @Override
    public List<GroupMemberResponse> getGroupMemberList(Long groupId) {

        return groupMemberRepository.findMembersWithName(groupId);
    }

    @Override
    public GroupMember save(GroupMember groupMember) {
       return groupMemberRepository.save(groupMember);
    }

    @Transactional
    @Override
    public void deleteGroupMember(Long groupId, String userId) {
        groupMemberRepository.deleteByGroupIdAndUserId(groupId,userId);
    }
}
