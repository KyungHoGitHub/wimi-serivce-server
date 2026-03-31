package com.example.service.domain.dailyImage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DailyImageCreateRequestDTO {
    private Long dailyId;
    private MultipartFile url;
    private Long orderIndex;
}
