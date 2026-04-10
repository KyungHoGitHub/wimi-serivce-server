package com.example.service.domain.meetingDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingDetailCreateRequestDTO {

    private Long meetingId;
    private String type;
    private String value;
    private String displayValue;

}
