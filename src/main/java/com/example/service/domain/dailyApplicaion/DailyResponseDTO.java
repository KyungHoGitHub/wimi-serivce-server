package com.example.service.domain.dailyApplicaion;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyResponseDTO {
    private Long id;
    private String title;
    private String name;
    private String content;
    private String imageUrl;
    private String thumbnailUrl;
    private List<ImageDTO> images;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private Long commentCount;  // 추가
    private Long likeCount;     // 추가
    private boolean isOwner;
    private String authorNickname;
    private String authorImageUrl;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageDTO {  // ✅ static 필수
        private String url;
        private Long orderIndex;
    }
}