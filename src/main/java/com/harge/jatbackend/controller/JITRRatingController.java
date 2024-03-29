package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.model.JITRRating;
import com.harge.jatbackend.service.JITRRatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200") 
public class JITRRatingController 
{
    @Autowired
    private JITRRatingService jitrRatingService;

    // get all JITR Ratings
    @GetMapping("/jitr-ratings")
    public List<JITRRating> getAllJITRRatings()
    {
        return jitrRatingService.findAllJITRRatings();
    }

    // add JITR Rating
    @PostMapping("/jitr-ratings")
    public JITRRating addJITRRating(@RequestBody JITRRating jitrRating)
    {
        return jitrRatingService.saveJITRRating(jitrRating);
    }
}