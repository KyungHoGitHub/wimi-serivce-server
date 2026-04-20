package com.example.service.domain.meeting;

import com.example.service.domain.dailyApplicaion.DailyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService{
    private final MeetingRepository meetingRepository;


    @Override
    public Meeting createMeeting(MeetingCreateRequestDTO requestDTO) {

        Meeting meeting  = Meeting.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())

                .groupId(requestDTO.getGroupId())
                .startAt(requestDTO.getStartAt())
                .endAt(requestDTO.getEndAt())
                .createdBy(requestDTO.getCreatedUserId())
                .build();
        return  meetingRepository.save(meeting);

    }

    @Override
    public List<MeetingResponseDTO> getMeetingList(String userId) {
        return meetingRepository.findMeetingListByUserId(userId)
                .stream()
                .map(p -> MeetingResponseDTO.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .startAt(p.getStartAt())
                        .endAt(p.getEndAt())
                        .groupName(p.getGroupName())
                        .groupId(p.getGroupId())
                        .nickname(p.getCreatorNickname())
                        .imageUrl(p.getCreatorProfileUrl())
                        .build())
                .toList();
    }

}
