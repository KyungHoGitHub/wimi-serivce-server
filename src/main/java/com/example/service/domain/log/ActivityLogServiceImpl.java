package com.example.service.domain.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository activityLogRepository;

    @Override
    public void saveActivityLog(ActivityLog activityLog) {
        activityLogRepository.save(activityLog);
    }
}
