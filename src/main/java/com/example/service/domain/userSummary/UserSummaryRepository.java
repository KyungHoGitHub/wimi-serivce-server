package com.example.service.domain.userSummary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSummaryRepository extends JpaRepository<UserSummary, String> {
    UserSummary findByPhoneNumber(String phoneNumber);
}
