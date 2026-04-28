package com.example.service.domain.daily.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DailyUpdateRequestDTO {
    private String content;
    private MultipartFile image;
}
