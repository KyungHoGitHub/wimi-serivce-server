package com.example.service.domain.meetingApplication;

import com.example.service.domain.meeting.MeetingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MeetingApplicationController {
    private final MeetingApplicationService meetingApplicationService;

    @PostMapping("/api/meeting")
    public ResponseEntity<?> createMeetingApplication(@RequestBody MeetingApplicationCreateRequestDTO requestDTO,
                                                      @AuthenticationPrincipal String userId){
        requestDTO.setCreatedUserId(userId);
        meetingApplicationService.createMeetingApplication(requestDTO);
        return ResponseEntity.ok("success");

    }

    @GetMapping("/api/meetings")
    public ResponseEntity<List<MeetingResponseDTO>> getMeetingList(@AuthenticationPrincipal String userId,
                                                                   @RequestParam(required = false) Integer limit){
        List<MeetingResponseDTO> result = meetingApplicationService.getMeetingList(userId);

        if(limit != null){
            return ResponseEntity.ok(result.stream().limit(limit).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(result);
    }

}
