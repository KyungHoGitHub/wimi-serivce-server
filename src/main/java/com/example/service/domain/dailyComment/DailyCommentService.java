package com.example.service.domain.dailyComment;

import com.example.service.domain.dailyApplicaion.DailyResponseDTO;

import java.util.List;

public interface DailyCommentService {

    List<DailyCommentResponseDTO> getComments(Long dailyId);
    DailyCommentResponseDTO createComment(Long dailyId,DailyCommentCreateRequestDTO requestDTO,String userId);
}
