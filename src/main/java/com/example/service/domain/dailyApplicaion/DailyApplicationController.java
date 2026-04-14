package com.example.service.domain.dailyApplicaion;

import com.example.service.domain.daily.DailyCreateRequestDTO;
import com.example.service.domain.group.Group;
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

    @PostMapping(value = "/api/daily", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>  createDaily(@AuthenticationPrincipal String userId,@ModelAttribute DailyCreateRequestDTO requestDTO)  throws IOException {
        requestDTO.setCreateUserId(userId);
        dailyApplicationService.createDaily(requestDTO);

        return ResponseEntity.ok("success");
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
    public ResponseEntity<DailyResponseDTO> getDailyDetail(@PathVariable("dailyId") Long dailyId) {
        return ResponseEntity.ok(dailyApplicationService.getDailyDetail(dailyId));
    }
}
