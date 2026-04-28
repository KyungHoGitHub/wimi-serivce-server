package com.example.service.domain.dailyImage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyImageRepository extends JpaRepository<DailyImage, Long> {
    DailyImage findByDailyId(Long dailyId);
}
