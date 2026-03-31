package com.example.service.domain.daily;

import com.example.service.domain.group.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DailyController {
    private final DailyService dailyService;

    @PostMapping("/api/daily")
    public ResponseEntity<?> createDaily(@RequestBody DailyCreateRequestDTO requestDTO){
        dailyService.createDaily(requestDTO);
        return ResponseEntity.ok("success");
    }

}
