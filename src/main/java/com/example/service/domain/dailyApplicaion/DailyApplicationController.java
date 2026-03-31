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
    public ResponseEntity<List<DailyResponseDTO>> getDailyList(@AuthenticationPrincipal String userId){
        return ResponseEntity.ok(dailyApplicationService.getDailyList(userId));

    }

    @GetMapping("/api/daily/{dailyId}")
    public ResponseEntity<DailyResponseDTO> getDailyDetail(@PathVariable("dailyId") Long dailyId) {
        return ResponseEntity.ok(dailyApplicationService.getDailyDetail(dailyId));
    }
}
