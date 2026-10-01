package com.example.service.domain.foodSpot;

import com.example.service.common.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_spot")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class FoodSpot extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String menu;
    private String address;
    private String review;

    @Column(name="image_key")
    private String imageKey;
    private Double lat;
    private Double lng;

    @Column(name="created_by")
    private String createdBy;

    @OneToMany(mappedBy = "foodSpot", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<FoodSpotMenuBoard> menus = new ArrayList<>();


    @Builder
    private FoodSpot(String name, String menu, String address, String review, String imageKey, Double lat, Double lng, String createdBy) {
        this.name = name;
        this.menu = menu;
        this.address = address;
        this.review = review;
        this.imageKey = imageKey;
        this.lat = lat;
        this.lng = lng;
        this.createdBy = createdBy;
    }

    public void update(String name, String menu, String address, String review,
                       String imageKey, Double lat, Double lng) {
        this.name = name;
        this.menu = menu;
        this.address = address;
        this.review = review;
        this.imageKey = imageKey;
        this.lat = lat;
        this.lng = lng;
    }


    public void replaceMenus(List<FoodSpotMenuBoard> newMenus) {
        this.menus.clear();          // orphanRemoval로 기존 메뉴판 삭제
        newMenus.forEach(this::addMenu);
    }

    public void addMenu(FoodSpotMenuBoard menu) {
        this.menus.add(menu);
        menu.assignFoodSpot(this);
    }

    public boolean isCreatedBy(String userId){
        return this.createdBy.equals(userId);
    }
}
