package com.example.service.domain.dailyComment;

import lombok.Getter;

@Getter
public class DailyCommentCreateRequestDTO {
    private String content;
    private Long parentId;
}
