package com.example.service.domain.group;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class GroupInvite {
    @Id
    private int id;

    @Column(name="inited_user_id")
    private String invitedUserId;

    @Column(name="invited_email")
    private String invitedEmail;

    @Column(name="invited_by")
    private String invitedBy;

    private String status;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
