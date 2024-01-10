package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import com.harge.jatbackend.model.JITRRating;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JITRRatingServiceTest 
{
    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Autowired
    private JITRRatingService jitrRatingService;

    @Test
    void findAllJITRRatings()
    {
        List<JITRRating> jitrRatingList = jitrRatingService.findAllJITRRatings();
        
        assertFalse(jitrRatingList.isEmpty());
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    void saveJITRRating()
    {
        JITRRating jitrRating = new JITRRating();
        jitrRating.setRatingDescription("Test");

        jitrRatingService.saveJITRRating(jitrRating);
    }
}