package com.example.service.domain.daily.dto;

import com.example.service.domain.daily.Daily;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "일상 생성 응답")
public class DailyCreateResponse {

    @Schema(description = "생성된 일상 ID", example ="1")
    Long id;

    @Schema(description = "일상이 속하는 그룹 ID", example ="1")
    Long groupId;

    @Schema(description = "일상 생성자 ID")
    String createdUserId;

    @Schema(description = "일상 내용", example = "오늘은 즐거운 하루 입니다.")
    String content;


    String locationTag;

    @Schema(description = "공개 범위", example = "PUBLIC")
    String scope;

    @Schema(description = "생성 일시", example = "2023-01-01T00:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDateTime createdAt;

    public static DailyCreateResponse from(Daily daily) {
        return DailyCreateResponse.builder()
                .id(daily.getId())
                .groupId(daily.getGroupId())
                .createdUserId(daily.getCreatedUserId())
                .content(daily.getContent())
                .scope(daily.getScope())
                .createdAt(daily.getCreatedAt())
                .build();
    }
}
