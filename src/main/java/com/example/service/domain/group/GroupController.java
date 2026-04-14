package com.example.service.domain.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<List<GroupListResponseDTO>> allGroups(@AuthenticationPrincipal String userId ){

        return ResponseEntity.ok(groupService.getGroups(userId));
    }

    @GetMapping("/api/groups/{groupId}")
    public ResponseEntity<Group> getGroupDetail(@PathVariable("groupId") Long groupId){
        return ResponseEntity.ok(groupService.getGroupDetail(groupId));
    }

    @DeleteMapping("/api/group/{groupId}")  // ✅ groupId로 통일
    public ResponseEntity<?> deleteGroup(@PathVariable("groupId") Long groupId, @AuthenticationPrincipal String userId) {
        groupService.deleteGroup(groupId, userId);
        return ResponseEntity.ok("success");
    }

    @PatchMapping(value="/api/group/modify/{groupId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateGroup(@PathVariable("groupId")Long groupId,@ModelAttribute GroupUpdateRequestDTO requestDTO) throws IOException {
        groupService.updateGroup(groupId,requestDTO);
        return ResponseEntity.ok("success");
    }

}
