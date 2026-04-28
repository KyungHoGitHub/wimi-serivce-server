package com.example.service.domain.userSummary;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSummaryRepository extends JpaRepository<UserSummary, String> {
    UserSummary findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query("UPDATE UserSummary u SET u.profileImageUrl = :imageUrl WHERE u.userId = :userId")
    void updateProfileImage(@Param("imageUrl") String imageUrl, @Param("userId") String userId);
}
