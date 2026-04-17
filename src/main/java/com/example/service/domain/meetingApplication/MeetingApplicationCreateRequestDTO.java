package com.example.service.domain.meetingApplication;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MeetingApplicationCreateRequestDTO {
    String title;
    String name;
    String content;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String createdUserId;
    Long groupId;                          // 단일 그룹
    List<String>   memberIds;      // 참여자 여러명
}
