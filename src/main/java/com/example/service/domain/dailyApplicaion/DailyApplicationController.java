package com.example.service.domain.dailyApplicaion;

import com.example.service.common.response.CommonResponse;
import com.example.service.common.response.SuccessCode;
import com.example.service.domain.daily.dto.DailyCreateRequestDTO;
import com.example.service.domain.daily.dto.DailyCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DailyApplicationController {
    private final DailyApplicationService dailyApplicationService;

    @Operation(summary = "일상 생성", description = "새로운 일상을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "생성 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @PostMapping(value = "/api/daily", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonResponse<DailyCreateResponse>> createDaily(@AuthenticationPrincipal String userId, @Valid @ModelAttribute DailyCreateRequestDTO requestDTO) {
        return ResponseEntity.status(SuccessCode.DAILY_CREATED.getStatus())
                .body(CommonResponse.of(SuccessCode.DAILY_CREATED, dailyApplicationService.createDaily(requestDTO, userId)));
    }

    @GetMapping("/api/daily")
    public ResponseEntity<List<DailyResponseDTO>> getDailyList(
            @AuthenticationPrincipal String userId,
            @RequestParam(required = false) Integer limit
    ) {
        List<DailyResponseDTO> result = dailyApplicationService.getDailyList(userId);

        if (limit != null) {
            return ResponseEntity.ok(result.stream().limit(limit).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/daily/{dailyId}")
    public ResponseEntity<DailyResponseDTO> getDailyDetail(@PathVariable("dailyId") Long dailyId, @AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(dailyApplicationService.getDailyDetail(dailyId, userId));
    }
}
