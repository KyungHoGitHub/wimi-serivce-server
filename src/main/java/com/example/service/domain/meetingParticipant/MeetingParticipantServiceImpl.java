package com.example.service.domain.meetingParticipant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void createMeetingParticipants(List<MeetingParticipantCreateRequestDTO> dtos) {
        List<MeetingParticipant> participants = dtos.stream()
                .map(dto -> MeetingParticipant.builder()
                        .meetingId(dto.getMeetingId())
                        .userId(dto.getUserId())
                        .status(dto.getStatus())
                        .build())
                .collect(Collectors.toList());

        meetingParticipantRepository.saveAll(participants);
    }

    @Override
    public List<MeetingParticipantProjection> getMeetingParticipatsList(List<Long> meetingIds) {

       return meetingParticipantRepository.findParticipantsByMeetingIds(meetingIds);
    }
}
