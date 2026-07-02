package com.example.service.domain.groupInvite;

import com.example.service.domain.group.GroupMember;
import com.example.service.domain.group.Role;
import com.example.service.domain.group.Status;
import com.example.service.domain.groupMember.GroupMemberService;
import com.example.service.domain.notification.Notification;
import com.example.service.domain.notification.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupInviteServiceImpl implements GroupInviteService {

    private final GroupInviteRepository groupInviteRepository;
    private final NotificationService notificationService;
    private final GroupMemberService groupMemberService;
    @Transactional
    @Override
    public void createGroupInvite(GroupInviteCreateRequestDTO requestDTO) {
        GroupInvite groupInvite = GroupInvite.builder()
                .invitedUserId(requestDTO.getInvitedUserId())
                .invitedBy(requestDTO.getInvitedBy())
                .groupId(requestDTO.getGroupId())
                .status(requestDTO.getStatus())
                .expiredAt(LocalDateTime.now().plusDays(7))
                .build();
        groupInviteRepository.save(groupInvite);
        // 알림 저장
        Notification notification = Notification.builder()
                .userId(requestDTO.getInvitedUserId())
                .type("INVITE")
                .title("그룹 초대")
                .body("그룹에 초대되었습니다.")
                .referenceId(groupInvite.getId())
                .referenceType("GROUP_INVITE")
                .build();
        notificationService.save(notification);
    }

    @Transactional
    @Override
    public void acceptGroupInvite(Long inviteId, String userId) {
        GroupInvite groupInvite = groupInviteRepository.findById(inviteId).orElseThrow(()-> new RuntimeException("찾을수 없습니다."));
        groupInvite.setStatus("ACCEPTED");
        groupInviteRepository.save(groupInvite);


        GroupMember groupMember = GroupMember.builder()
                .groupId(groupInvite.getGroupId())
                .userId(userId)
                .role(Role.USER)
                .status(Status.IS_ABLED)
                .build();
        groupMemberService.save(groupMember);
    }

    @Override
    public void rejectGroupInvite(Long inviteId, String userId) {

    }
}
