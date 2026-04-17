package com.example.service.domain.meeting;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MeetingCreateRequestDTO {

    private String title;
    private String content;
    private String scope;
    private Long groupId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String createdUserId;

}
