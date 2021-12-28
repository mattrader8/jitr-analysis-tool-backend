package com.harge.jatbackend.service;

import java.util.List;

import com.harge.jatbackend.model.JITRRating;
import com.harge.jatbackend.repository.JITRRatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JITRRatingService 
{
    @Autowired
    JITRRatingRepository jitrRatingRepository;

    // find all JITR Ratings
    public List<JITRRating> findAllJITRRatings()
    {
        return jitrRatingRepository.findAll();
    }

    // save JITR Rating
    public JITRRating saveJITRRating(JITRRating jitrRating)
    {
        return jitrRatingRepository.save(jitrRating);
    }
}