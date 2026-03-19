package com.example.service.domain.userSummary;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "user_summary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary {

    @Id
    private String userId;
    private String nickname;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="description")
    private String description;

    @Column(name="profile_image_url")
    private String profileImageUrl;

    private String status;
    private String role;
}
