package com.example.service.domain.dailyApplicaion;

import java.time.LocalDateTime;

public interface DailyMyListProjection {

    Long getDailyId();
    String getImageUrl();
    Integer getImageCount();
    LocalDateTime getCreatedAt();

}
