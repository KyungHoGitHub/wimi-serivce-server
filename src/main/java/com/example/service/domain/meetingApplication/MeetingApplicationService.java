package com.example.service.domain.meetingApplication;

import com.example.service.domain.meeting.MeetingResponseDTO;

import java.util.List;

public interface MeetingApplicationService {

    void createMeetingApplication(MeetingApplicationCreateRequestDTO requestDTO);

    List<MeetingResponseDTO> getMeetingList(String userId);
}
