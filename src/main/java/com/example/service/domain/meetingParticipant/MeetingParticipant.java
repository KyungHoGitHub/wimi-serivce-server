package com.example.service.domain.meetingParticipant;

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
@Table(name = "meeting_participant")
public class MeetingParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meeting_id")
    private Long meetingId;

    @Column(name = "user_id")
    private String userId;

    private String status;

}
