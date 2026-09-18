package com.example.service.domain.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;

    @GetMapping("/api/kakao/geocode/reverse")
    public ResponseEntity<?> getGeoLocation(
            @RequestParam String lat,
            @RequestParam String lng
    ) {
        kakaoService.getAddressFromCoords(lat, lng);
        return ResponseEntity.ok("success");
    }
}
