package com.example.service.domain.meetingParticipant;

import java.util.List;

public interface MeetingParticipantService {
    MeetingParticipant createMeetingParticipant(MeetingParticipantCreateRequestDTO requestDTO);
    void createMeetingParticipants(List<MeetingParticipantCreateRequestDTO> dtos);
    List<MeetingParticipantProjection> getMeetingParticipatsList(List<Long> meetingIds);
}

