package com.example.service.domain.groupInvite;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GroupInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="inited_user_id")
    private String invitedUserId;

    @Column(name="invited_email")
    private String invitedEmail;

    @Column(name="invited_by")
    private String invitedBy;

    @Column(name="group_id")
    private Long groupId;

    private String status;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
