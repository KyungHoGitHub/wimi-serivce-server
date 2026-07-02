package com.example.service.domain.daily.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "일상 생성 요청 DTO")
public class DailyCreateRequestDTO {

    @NotNull(message = "그룹 ID는 필수 입니다.")
    @Schema(description = "그룹 ID", example = "1", required = true)
    Long groupId;


    String createUserId;

    @NotBlank(message = "내용은 필수 입니다.")
    @Size(max = 500, message = "내용은 500자 이내로 작성해주세요.")
    @Schema(description = "일상 내용 (최대 500자이내)", example = "새로운 일상의 시작입니다.", required = true)
    String content;

    @Schema(description = "공개 범위 (PUBLIC / GROUP / PRIVATE)", example = "GROUP")
    String scope;

    @Schema(description = "이미지 파일", example = "선택")
    List<MultipartFile> images;
}
