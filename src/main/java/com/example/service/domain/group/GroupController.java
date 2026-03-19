package com.example.service.domain.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody GroupCreateRequestDTO requestDTO){

        return null;
    }

    @GetMapping("/api/groups")
    public ResponseEntity<List<Group>> allGroups(@AuthenticationPrincipal String userId ){

        return ResponseEntity.ok(groupService.getGroups(userId));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGroup(){
        return null;
    }

    @PutMapping
    public ResponseEntity<?> updateGroup(){
        return null;
    }

}
