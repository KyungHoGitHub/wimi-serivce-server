package com.example.service.domain.dailyImage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyImageRepository extends JpaRepository<DailyImage, Long> {
    DailyImage findByDailyId(Long dailyId);
    List<DailyImage> findByDailyIdOrderByOrderIndexAsc(Long dailyId);
    void deleteByDailyId(Long dailyId);
}
