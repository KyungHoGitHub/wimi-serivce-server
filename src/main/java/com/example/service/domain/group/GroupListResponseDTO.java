package com.example.service.domain.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GroupListResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private String profileImageUrl;
    private String createdBy;
    private LocalDateTime createdAt;
    private int memberCount; // 추가
}
