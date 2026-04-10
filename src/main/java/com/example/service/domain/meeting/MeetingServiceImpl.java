package com.example.service.domain.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService{
    private final MeetingRepository meetingRepository;


    @Override
    public Meeting createMeeting(MeetingCreateRequestDTO requestDTO) {

        Meeting meeting  = Meeting.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .scope(requestDTO.getScope())
                .groupId(requestDTO.getGroupId())
                .startAt(requestDTO.getStartAt())
                .endAt(requestDTO.getEndAt())
                .createdBy(requestDTO.getCreatedUserId())
                .build();
        return  meetingRepository.save(meeting);

    }
}
