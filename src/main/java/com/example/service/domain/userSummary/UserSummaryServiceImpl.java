package com.example.service.domain.userSummary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSummaryServiceImpl implements UserSummaryService{
    private final UserSummaryRepository userSummaryRepository;

    @Override
    public UserSummary getUserSummary(String phoneNumber) {
        String formatted = phoneNumber.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
      return  userSummaryRepository.findByPhoneNumber(formatted);
    }
}
