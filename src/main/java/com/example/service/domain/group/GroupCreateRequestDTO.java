package com.example.service.domain.group;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupCreateRequestDTO {
    private String name;
    private String description;
    private Category category;
    private String profileImageUrl;
    private String createdBy;
}
