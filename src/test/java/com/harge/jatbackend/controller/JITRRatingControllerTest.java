package com.harge.jatbackend.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import com.harge.jatbackend.model.JITRRating;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JITRRatingControllerTest 
{
    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Autowired
    private JITRRatingController jitrRatingController;

    @Test
    void findAllJITRRatings()
    {
        List<JITRRating> jitrRatingList = jitrRatingController.getAllJITRRatings();
        
        assertFalse(jitrRatingList.isEmpty());
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    void saveJITRRating()
    {
        JITRRating jitrRating = new JITRRating();
        jitrRating.setRatingDescription("Test");

        jitrRatingController.addJITRRating(jitrRating);
    }
}