package com.example.service.domain.dailyComment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DailyCommentResponseDTO {
    private Long id;
    private String userId;
    private String nickName;        // ← 추가
    private String profileImageUrl; // ← 추가
    private String content;
    private Long parentId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
}
