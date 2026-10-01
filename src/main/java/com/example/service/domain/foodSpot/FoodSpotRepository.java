package com.example.service.domain.foodSpot;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodSpotRepository extends JpaRepository<FoodSpot, Long> {

    @EntityGraph(attributePaths = "menus")
    @Query("""
        SELECT fs FROM FoodSpot fs
        WHERE fs.createdBy = :userId
          AND (:keyword IS NULL
               OR fs.name LIKE CONCAT('%', :keyword, '%')
               OR fs.menu LIKE CONCAT('%', :keyword, '%'))
        """)
    Slice<FoodSpot> search(
            @Param("userId") String userId,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    void deleteByIdAndCreatedBy(Long id, String createdBy);

    @EntityGraph(attributePaths = "menus")
    Optional<FoodSpot> findWithMenusById(Long id);
}