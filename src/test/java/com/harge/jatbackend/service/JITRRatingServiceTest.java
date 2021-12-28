package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.harge.jatbackend.model.JITRRating;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JITRRatingServiceTest 
{
    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Autowired
    private JITRRatingService jitrRatingService;

    @Test
    public void testFindAllJITRRatings()
    {
        List<JITRRating> jitrRatingList = jitrRatingService.findAllJITRRatings();
        
        assertFalse(jitrRatingList.isEmpty());
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    public void testSaveJITRRating()
    {
        JITRRating jitrRating = new JITRRating();
        jitrRating.setRatingDescription("Test");

        jitrRatingService.saveJITRRating(jitrRating);
    }
}