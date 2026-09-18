package com.example.service.domain.dailyComment;

import com.example.service.domain.daily.DailyRepository;
import com.example.service.domain.userSummary.UserSummary;
import com.example.service.domain.userSummary.UserSummaryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyCommentServiceImpl implements DailyCommentService{
    private final DailyRepository dailyRepository;
    private final DailyCommentRepository dailyCommentRepository;
    private final UserSummaryRepository userSummaryRepository;

    @Override
    public List<DailyCommentResponseDTO> getComments(Long dailyId, String userId) {
        return dailyCommentRepository.findCommentsByDailyId(dailyId)
                .stream()
                .map(c -> DailyCommentResponseDTO.builder()
                        .id(c.getId())
                        .userId(c.getUserId())
                        .nickName(c.getNickName())
                        .profileImageUrl(c.getProfileImageUrl())
                        .content(c.getContent())
                        .isOwner(userId.equals(c.getUserId()))
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
    @Override
    public DailyCommentResponseDTO updateComment(Long commentId, DailyCommentUpdateRequestDTO requestDTO, String userId) {
        DailyComment comment = dailyCommentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        if (!comment.getUserId().equals(userId)) {
            throw new AccessDeniedException("본인 댓글만 수정할 수 있습니다.");
        }

        comment.setContent(requestDTO.getContent());
        DailyComment saved = dailyCommentRepository.save(comment);

        // user_summary에서 닉네임/프로필 조회
        UserSummary userSummary = userSummaryRepository.findById(saved.getUserId())
                .orElse(null); // 없으면 null 처리 (COALESCE로 fallback 하듯이)

        return DailyCommentResponseDTO.builder()
                .id(saved.getId())
                .userId(saved.getUserId())
                .nickName(userSummary != null ? userSummary.getNickname() : saved.getUserId()) // COALESCE 흉내
                .profileImageUrl(userSummary != null ? userSummary.getProfileImageUrl() : null)
                .content(saved.getContent())
                .parentId(saved.getParentId())
                .isOwner(true)
                .createdAt(saved.getCreatedAt())
                .build();
    }


    @Transactional
    @Override
    public void deleteComment(Long commentId, String userId) {
           Optional<DailyComment> dc = dailyCommentRepository.findById(commentId);

           if(!userId.equals(dc.get().getUserId())){
               throw new AccessDeniedException("삭제 권한이 없습니다.");
           }

           dailyCommentRepository.deleteById(commentId);
    }
}
