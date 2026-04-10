package com.example.service.domain.meetingDetail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingDetailServiceImpl implements MeetingDetailService{
    private final MeetingDetailRepository meetingDetailRepository;

    @Override
    public MeetingDetail createMeetingDetail(MeetingDetailCreateRequestDTO requestDTO) {

        MeetingDetail meetingDetail = MeetingDetail.builder()
                .meetingId(requestDTO.getMeetingId())
                .type(requestDTO.getType())
                .value(requestDTO.getValue())
                .displayValue(requestDTO.getDisplayValue())
                .build();
        return meetingDetailRepository.save(meetingDetail);
    }
}
