package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.Position;
import com.harge.jatbackend.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200") 
public class PositionController 
{
    @Autowired
    private PositionRepository positionRepository;

    // get all Positions
    @GetMapping("/positions")
    public List<Position> getAllPositions()
    {
        return positionRepository.findAll();
    }

    // get distinct LCATs
    @GetMapping("/positions/lcats")
    public List<Object> getDistinctLCATDescriptions() 
    {
        return positionRepository.findLCATDescriptions();
    }

    // get LCAT Levels by LCAT Description
    @GetMapping("/positions/{lcatDescription}")
    public List<Object> getLCATLevelsByLCATDescription(@PathVariable String lcatDescription)
    {
        List<Object> lcatLevels = null;
        try 
        {
            lcatLevels = positionRepository.findLCATLevels(lcatDescription);
        }
        
        catch (ResourceNotFoundException ex) 
        {
            ex.getMessage();
        }

        return lcatLevels;
    }

    // add Position
    @PostMapping("/positions")
    public Position addPosition(@RequestBody Position position)
    {
        return positionRepository.save(position);
    }
}