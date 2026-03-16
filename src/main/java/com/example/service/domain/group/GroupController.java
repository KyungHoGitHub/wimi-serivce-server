package com.example.service.domain.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody GroupCreateRequestDTO requestDTO){

        return null;
    }

    @GetMapping
    public ResponseEntity<?> allGroups(){
        return null;
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
