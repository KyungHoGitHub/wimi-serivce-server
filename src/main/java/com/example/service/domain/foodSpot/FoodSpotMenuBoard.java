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

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class FoodSpotMenuBoard  extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_spot_id",nullable = false)
    private FoodSpot foodSpot;

    @Column(name="order_index")
    private Integer orderIndex;

//    @CreationTimestamp
//    @Column(name="created_at")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name="updated_at")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDateTime updatedAt;

    @Builder
    private FoodSpotMenuBoard(String name, int price, int orderIndex) {
        this.name = name;
        this.price = price;
        this.orderIndex = orderIndex;
    }


    void assignFoodSpot(FoodSpot foodSpot) {
        this.foodSpot = foodSpot;
    }
}
