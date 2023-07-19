package com.example.businessproject.controller;

import com.example.businessproject.model.Business;
import com.example.businessproject.service.BusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/business")
public class BusinessController {
    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Business> create(@RequestBody Business business) {
        Business savedBusiness = businessService.save(business);
        return ResponseEntity.ok(savedBusiness);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Business> update(@RequestBody Business updatedBusiness) {

        if (updatedBusiness.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Business> existingBusiness = businessService.findById(updatedBusiness.getId());
        if (!existingBusiness.isPresent()) {
            return ResponseEntity.notFound().build();
        }


        Business business = existingBusiness.get();
        business.setName(updatedBusiness.getName());
        business.setLocation(updatedBusiness.getLocation());
        business.setLatitude(updatedBusiness.getLatitude());
        business.setLongitude(updatedBusiness.getLongitude());
        business.setCategory(updatedBusiness.getCategory());

        business = businessService.save(business);
        return ResponseEntity.ok(business);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Business> delete(@RequestBody Business business) {

        if (business.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Business> existingBusiness = businessService.findById(business.getId());
        if (!existingBusiness.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            businessService.delete(business);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Business>> search(
            @RequestParam(required = true) String location,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) String catergory,
                @RequestParam(required = false) String term
    ) {
        //List<Business> businesses = businessService.findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit( location, term, lat,longitude, catergory);
        List<Business> businesses = businessService.findAll();
        return ResponseEntity.ok(businesses);
    }
}