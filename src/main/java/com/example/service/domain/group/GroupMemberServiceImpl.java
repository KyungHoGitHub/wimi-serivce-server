package com.example.service.domain.group;

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
    public List<Integer> getGroupMembers(String userId) {
        List<Integer> groupIds = groupMemberRepository.findByUserId(userId)
                .stream()
                .map(GroupMember::getGroupId)
                .toList();


        return groupIds;
    }
}
