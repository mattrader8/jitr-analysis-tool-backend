package com.harge.jatbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.harge.jatbackend.model.JITRRating;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JITRRatingControllerTest 
{
    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Autowired
    private JITRRatingController jitrRatingController;

    @Test
    public void testFindAllJITRRatings()
    {
        List<JITRRating> jitrRatingList = jitrRatingController.getAllJITRRatings();
        
        assertFalse(jitrRatingList.isEmpty());
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    public void testSaveJITRRating()
    {
        JITRRating jitrRating = new JITRRating();
        jitrRating.setRatingDescription("Test");

        jitrRatingController.addJITRRating(jitrRating);
    }
}