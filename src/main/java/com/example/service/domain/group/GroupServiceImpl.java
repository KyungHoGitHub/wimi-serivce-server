package com.example.service.domain.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

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
}
