package com.example.service.domain.meetingApplication;

import com.example.service.domain.meeting.MeetingService;
import com.example.service.domain.meetingDetail.MeetingDetailService;
import com.example.service.domain.meetingParticipant.MeetingParticipantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingApplicationServiceImpl implements MeetingApplicationService {
    private MeetingService meetingService;
    private MeetingDetailService meetingDetailService;
    private MeetingParticipantService meetingParticipantService;

    @Transactional
    @Override
    public void createMeetingApplication(MeetingApplicationCreateRequestDTO requestDTO) {
        // 약속(Meeting) 생성



    }
}
