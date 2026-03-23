package com.example.service.domain.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupMemberController {
    private final GroupMemberService groupMemberService;

    @GetMapping("/api/group-member-list")
    public ResponseEntity<List<GroupMemberResponse>> getGroupMemberList(Integer groupId){
        return ResponseEntity.ok(groupMemberService.getGroupMemberList(groupId));
    }
}
