package com.example.service.domain.meetingApplication;

import com.example.service.domain.meeting.MeetingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<MeetingResponseDTO>> getMeetingList(@AuthenticationPrincipal String userId){

        return ResponseEntity.ok(meetingApplicationService.getMeetingList(userId));
    }

}
