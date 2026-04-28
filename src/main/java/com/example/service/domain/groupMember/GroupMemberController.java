package com.example.service.domain.groupMember;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupMemberController {
    private final GroupMemberService groupMemberService;

    @GetMapping("/api/group-member-list")
    public ResponseEntity<List<GroupMemberResponse>> getGroupMemberList(Long groupId){
        return ResponseEntity.ok(groupMemberService.getGroupMemberList(groupId));
    }

    @DeleteMapping("/api/group-member/{groupId}")
    public ResponseEntity<?> deleteGroupMember(@PathVariable("groupId") Long groupId, @AuthenticationPrincipal String userId){
        groupMemberService.deleteGroupMember(groupId,userId);
        return ResponseEntity.ok("success");
    }
}
