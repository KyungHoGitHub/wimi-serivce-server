package com.example.service.domain.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupApplicationController {
    private  final GroupApplicationService groupApplicationService;

    @PostMapping("/group/group-member")
    ResponseEntity<?> createGroupAndGroupMember(@RequestBody GroupCreateRequestDTO requestDTO){
        groupApplicationService.createGroup(requestDTO);
        return ResponseEntity.ok("suceess");
    }
}
