package com.example.service.domain.daily.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "일상 수정 요청 DTO")
public class DailyUpdateRequestDTO {

    @Schema(description = "일상 내용 (최대 500자이내)", example = "새로운 일상의 시작입니다.")
    String content;

    @Schema(description = "이미지 파일", example = "선택")
    List<MultipartFile> images;
}
