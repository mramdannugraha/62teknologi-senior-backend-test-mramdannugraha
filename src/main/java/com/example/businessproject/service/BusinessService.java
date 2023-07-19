package com.example.businessproject.service;


import com.example.businessproject.BusinessRepository;
import com.example.businessproject.model.Business;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {
    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

   public List<Business> findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit(
           String location, String term, Double lat,Double longitude, String catergory) {
        return businessRepository.findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit( location,term, lat, longitude,catergory);
    }

    public Optional<Business> findById(Long id) {
        return businessRepository.findById(id);
    }

    public Business save(Business business) {
        return businessRepository.save(business);
    }

    public void delete(Business business) {
        businessRepository.delete(business);
    }

    public List<Business> findAll() {
        return businessRepository.findAll();
    }


}