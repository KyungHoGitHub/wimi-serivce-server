package com.example.service.domain.userSummary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class UserSummaryUpdateRequestDTO {
    private String nickname;
    private String description;
    private MultipartFile profileImage;
}
