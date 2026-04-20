package com.example.service.domain.meeting;

import com.example.service.domain.meetingParticipant.MeetingParticipantProjection;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String scope;
    private String groupName;
    private Long groupId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endAt;
    private String createBy;  // 추가
    private String nickname;
    private String imageUrl;
    private List<MeetingParticipantProjection> participants;
}
