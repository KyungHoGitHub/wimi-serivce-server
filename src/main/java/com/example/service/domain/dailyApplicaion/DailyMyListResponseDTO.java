package com.example.service.domain.dailyApplicaion;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyMyListResponseDTO {

    Long dailyId;

    String imageUrl;

    Integer imageCount;

    LocalDateTime createdAt;

}
