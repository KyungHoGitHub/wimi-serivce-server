package com.example.service.domain.dailyApplicaion;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private Long commentCount;  // 추가
    private Long likeCount;     // 추가
}
