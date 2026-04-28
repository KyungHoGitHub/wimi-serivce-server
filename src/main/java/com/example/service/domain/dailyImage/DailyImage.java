package com.example.service.domain.dailyImage;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "daily_image")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="daily_id")
    private Long dailyId;

    private String url;

    @Column(name="order_index")
    private Long orderIndex;

    @CreationTimestamp
    @Column(name="created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
}
