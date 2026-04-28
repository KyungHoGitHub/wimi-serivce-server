package com.example.service.domain.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "activity_log")
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id")
    String userId;

    String action;

    @Column(name = "target_id")
    Long targetId;

    String status;

    @Column(name = "error_msg")
    String errorMsg;

    @CreationTimestamp
    @Column(name="created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate createdAt;

}
