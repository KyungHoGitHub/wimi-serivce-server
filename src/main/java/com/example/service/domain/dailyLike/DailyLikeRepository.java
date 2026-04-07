package com.example.service.domain.dailyLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyLikeRepository extends JpaRepository<DailyLike, Long> {
    Long countByDailyId(Long dailyId);
    Optional<DailyLike> findByDailyIdAndUserId(Long dailyId,String userId);
}
