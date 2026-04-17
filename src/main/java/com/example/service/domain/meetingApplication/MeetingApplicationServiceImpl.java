package com.example.service.domain.meetingApplication;

import com.example.service.domain.meeting.Meeting;
import com.example.service.domain.meeting.MeetingCreateRequestDTO;
import com.example.service.domain.meeting.MeetingResponseDTO;
import com.example.service.domain.meeting.MeetingService;
import com.example.service.domain.meetingDetail.MeetingDetailService;
import com.example.service.domain.meetingParticipant.MeetingParticipantCreateRequestDTO;
import com.example.service.domain.meetingParticipant.MeetingParticipantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingApplicationServiceImpl implements MeetingApplicationService {
    private final MeetingService meetingService;
    private final MeetingDetailService meetingDetailService;
    private final MeetingParticipantService meetingParticipantService;

    @Transactional
    @Override
    public void createMeetingApplication(MeetingApplicationCreateRequestDTO requestDTO) {

        MeetingCreateRequestDTO meetingCreateRequestDTO = MeetingCreateRequestDTO.builder()
                .title(requestDTO.getName())
                .content(requestDTO.getContent())
                .groupId(requestDTO.getGroupId())
                .createdUserId(requestDTO.getCreatedUserId())
                .startAt(requestDTO.getStartDate())
                .endAt(requestDTO.getEndDate())
                .build();
        Meeting meeting = meetingService.createMeeting(meetingCreateRequestDTO);

        List<MeetingParticipantCreateRequestDTO> participants = requestDTO.getMemberIds()
                .stream()
                .filter(memberId -> !memberId.equals(requestDTO.getCreatedUserId())) // 생성자 중복 제외
                .map(memberId -> MeetingParticipantCreateRequestDTO.builder()
                        .meetingId(meeting.getId())
                        .userId(String.valueOf(memberId))
                        .status("PENDING")
                        .build())
                .collect(Collectors.toList());

        meetingParticipantService.createMeetingParticipants(participants);



    }

    @Override
    public List<MeetingResponseDTO> getMeetingList(String userId) {
        return meetingService.getMeetingList(userId);
    }
}
