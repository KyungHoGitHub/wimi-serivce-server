package com.example.service.domain.daily;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DailyCreateRequestDTO {

    private Long groupId;
    private String createUserId;
    private String title;
    private String content;
    private String scope;
    private MultipartFile image;
}
