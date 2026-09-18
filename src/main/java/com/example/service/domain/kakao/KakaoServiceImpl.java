package com.example.service.domain.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService{
    
    @Override
    public String getAddressFromCoords(String lat, String lng) {
        return "";
    }
}
