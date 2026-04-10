package com.example.service.domain.meetingParticipant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingParticipantServiceImpl implements MeetingParticipantService{
    private final MeetingParticipantRepository meetingParticipantRepository;

    @Override
    public MeetingParticipant createMeetingParticipant(MeetingParticipantCreateRequestDTO requestDTO) {

        MeetingParticipant meetingParticipant = MeetingParticipant.builder()
                .meetingId(requestDTO.getMeetingId())
                .userId(requestDTO.getUserId())
                .status(requestDTO.getStatus())
                .build();
        return meetingParticipantRepository.save(meetingParticipant);
    }
}
