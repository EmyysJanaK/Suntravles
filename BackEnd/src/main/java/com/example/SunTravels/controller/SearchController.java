package com.example.SunTravels.controller;

import com.example.SunTravels.dto.request_body.Criteria;
import com.example.SunTravels.dto.response_body.SearchResultDTO;
import com.example.SunTravels.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public ResponseEntity<List<SearchResultDTO>> getAllResults( @RequestBody Criteria criteria) {
        List<SearchResultDTO> filteredResults = searchService.filterRoomTypesByCriteria(criteria);
        return ResponseEntity.ok(filteredResults);
    }


}
