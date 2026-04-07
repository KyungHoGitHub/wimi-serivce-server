package com.example.service.domain.meetingDetail;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Table(name = "meeting_detail")
public class MeetingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meeting_id")
    private Long meetingId;

    private String type;
    private String value;

    @Column(name = "display_value")
    private String displayValue;
}
