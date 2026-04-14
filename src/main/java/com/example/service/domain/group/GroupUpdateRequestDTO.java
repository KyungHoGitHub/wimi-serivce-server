package com.example.service.domain.group;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class GroupUpdateRequestDTO {

        private String name;
        private String content;

        private MultipartFile image;

}
