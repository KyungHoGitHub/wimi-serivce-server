package com.example.service.domain.groupApplicaion;

import com.example.service.domain.group.GroupCreateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupApplicationController {
    private final GroupApplicationService groupApplicationService;

    @PostMapping("/api/group/group-member")
    ResponseEntity<?> createGroupAndGroupMember(
            @RequestBody GroupCreateRequestDTO requestDTO,
            @AuthenticationPrincipal String userId) {
        requestDTO.setCreatedBy(userId);
        groupApplicationService.createGroup(requestDTO);
        return ResponseEntity.ok("suceess");
    }
}
