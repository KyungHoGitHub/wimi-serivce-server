package com.example.service.domain.daily;

import com.example.service.domain.daily.dto.DailyCreateRequestDTO;
import com.example.service.domain.daily.dto.DailyUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DailyController {
    private final DailyService dailyService;

    @PostMapping("/api/daily")
    public ResponseEntity<?> createDaily(@RequestBody DailyCreateRequestDTO requestDTO, @AuthenticationPrincipal String userId){
        dailyService.createDaily(requestDTO,userId);
        return ResponseEntity.ok("success");
    }

    @PutMapping(value = "/api/daily/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateDaily(@PathVariable("id") Long dailyId, @AuthenticationPrincipal String userId ,
                                         @ModelAttribute DailyUpdateRequestDTO requestDTO ){
        dailyService.updateDaily(dailyId,userId,requestDTO);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/api/daily/{dailyId}")
    public ResponseEntity<?> deleteDaily(@PathVariable("dailyId") Long dailyId, @AuthenticationPrincipal String userId){
        dailyService.deleteDaily(dailyId,userId);
        return ResponseEntity.ok("success");
    }
}

