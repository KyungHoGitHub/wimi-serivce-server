package com.example.service.domain.groupInvite;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GroupInviteController {

    private final GroupInviteService groupInviteService;


    @PostMapping("api/group/invite")
     public ResponseEntity<?> inviteMember(@RequestBody GroupInviteCreateRequestDTO requestDTO, @AuthenticationPrincipal String userId){
        requestDTO.setInvitedBy(userId);
        groupInviteService.createGroupInvite(requestDTO);
        return ResponseEntity.ok("success");
    }

    @PatchMapping("api/group-invite/{inviteId}/accept")
    public ResponseEntity<?> acceptGroupInvite(@PathVariable("inviteId") Long inviteId, @AuthenticationPrincipal String userId){
        groupInviteService.acceptGroupInvite(inviteId,userId);
        return ResponseEntity.ok("success");
    }

    @PatchMapping("api/group-invite/{inviteId}/reject")
    public ResponseEntity<?> rejectGroupInvite(@PathVariable("inviteId") Long inviteId, @AuthenticationPrincipal String userId){

        return ResponseEntity.ok("success");
    }
}
