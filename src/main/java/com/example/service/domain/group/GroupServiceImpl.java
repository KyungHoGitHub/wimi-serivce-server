package com.example.service.domain.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    @Override
    public void createGroup(GroupRequestDTO requestDTO) {


    }
}
