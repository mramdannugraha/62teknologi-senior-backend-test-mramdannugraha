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

  /*  @Query("select p from business p where (p.location like :location or :location is null) ")
    List<Business> findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit(
            @Param("location") String location);*/

}