package com.example.service.domain.group;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    private int id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name= "profile_imgae_url")
    private String profileImageUrl;

    @Column(name = "created_by")
    private String createdBy;

    @CreationTimestamp
    @Column(name="created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name="updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;
}
