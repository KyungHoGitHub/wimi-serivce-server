package com.example.service.domain.meetingApplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingApplicationController {
    private final MeetingApplicationService meetingApplicationService;

    public ResponseEntity<?> createMeetingApplication(@RequestBody MeetingApplicationCreateRequestDTO requestDTO,
                                                      @AuthenticationPrincipal String userId){

        return ResponseEntity.ok("success");

    }

}
