package com.example.businessproject;

import com.example.businessproject.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findById(Long id);

    @Query("SELECT p FROM Business p WHERE (p.location LIKE :location OR :location IS NULL) " +
            "AND (p.term LIKE CONCAT('%', COALESCE(:term, ''), '%') OR :term IS NULL) " +
            "AND (p.latitude = COALESCE(:lat, p.latitude) OR :lat IS NULL) " +
            "AND (p.longitude = COALESCE(:longitude, p.longitude) OR :longitude IS NULL) " +
            "AND (p.category LIKE CONCAT('%', COALESCE(:category, ''), '%') OR :category IS NULL)")
    List<Business> findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit(
            @Param("location") String location,
            @Param("term") String term,
            @Param("lat") Double lat,
            @Param("longitude") Double longitude,
            @Param("category") String category);
}