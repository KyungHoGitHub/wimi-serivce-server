package com.example.service.domain.meeting;

import java.util.List;

public interface MeetingService {
    Meeting createMeeting(MeetingCreateRequestDTO requestDTO);
    List<MeetingResponseDTO> getMeetingList(String userId);
}
