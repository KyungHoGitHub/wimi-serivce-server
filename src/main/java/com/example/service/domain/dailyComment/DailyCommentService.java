package com.example.service.domain.dailyComment;

import com.example.service.domain.dailyApplicaion.DailyResponseDTO;

import java.util.List;

public interface DailyCommentService {

    List<DailyCommentResponseDTO> getComments(Long dailyId,String userId);
    DailyCommentResponseDTO createComment(Long dailyId,DailyCommentCreateRequestDTO requestDTO,String userId);

    DailyCommentResponseDTO updateComment(Long commentId,DailyCommentUpdateRequestDTO requestDTO,String userId);
    void deleteComment(Long commentId,String userId);
}
