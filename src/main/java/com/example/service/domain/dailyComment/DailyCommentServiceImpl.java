package com.example.service.domain.dailyComment;

import com.example.service.domain.daily.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyCommentServiceImpl implements DailyCommentService{
    private final DailyRepository dailyRepository;
    private final DailyCommentRepository dailyCommentRepository;

    @Override
    public List<DailyCommentResponseDTO> getComments(Long dailyId) {
        return dailyCommentRepository.findCommentsByDailyId(dailyId)
                .stream()
                .map(c -> DailyCommentResponseDTO.builder()
                        .id(c.getId())
                        .userId(c.getUserId())
                        .nickName(c.getNickName())
                        .profileImageUrl(c.getProfileImageUrl())
                        .content(c.getContent())
                        .parentId(c.getParentId())
                        .createdAt(c.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public DailyCommentResponseDTO createComment(Long dailyId, DailyCommentCreateRequestDTO requestDTO, String userId) {
       DailyComment comment = DailyComment.builder()
               .dailyId(dailyId)
               .userId(userId)
               .content(requestDTO.getContent())
               .parentId(requestDTO.getParentId())
               .createdAt(LocalDateTime.now())
               .build();
       DailyComment saved = dailyCommentRepository.save(comment);

        return DailyCommentResponseDTO.builder()
                .id(saved.getId())
                .userId(saved.getUserId())
                .content(saved.getContent())
                .parentId(saved.getParentId())
                .createdAt(saved.getCreatedAt())
                .build();
    }
}
