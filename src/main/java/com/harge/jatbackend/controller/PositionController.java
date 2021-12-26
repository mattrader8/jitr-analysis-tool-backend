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
    public List<String> getDistinctLCATDescriptions() 
    {
        return positionRepository.findLCATDescriptions();
    }

    // get LCAT Levels by LCAT Description for cancelled JITRs
    @GetMapping("/positions/cancelled/{lcatDescription}")
    public List<String> getLCATLevelsByLCATDescriptionForCancelledJITRs(@PathVariable String lcatDescription)
    {
        List<String> lcatLevels = null;

        List<Position> positionList = positionRepository.findPositionsByLCATDescription(lcatDescription);

        if (positionList.isEmpty())
        {
            throw new ResourceNotFoundException("Position does not exist with LCAT Description :" + lcatDescription);
        }

        else
        {
            lcatLevels = positionRepository.findLCATLevelsForCancelledJITRs(lcatDescription);
        }

        return lcatLevels;
    }

    // get LCAT Levels by LCAT Description for active JITRs ('Awarded' and 'Declined' JITR Statuses)
    @GetMapping("/positions/{lcatDescription}")
    public List<String> getLCATLevelsByLCATDescriptionForActiveJITRs(@PathVariable String lcatDescription)
    {
        List<String> lcatLevels = null;
        
        List<Position> positionList = positionRepository.findPositionsByLCATDescription(lcatDescription);

        if (positionList.isEmpty())
        {
            throw new ResourceNotFoundException("Position does not exist with LCAT Description :" + lcatDescription);
        }

        else
        {
            lcatLevels = positionRepository.findLCATLevelsForActiveJITRs(lcatDescription);
        }

        return lcatLevels;
    }

    // get Position by LCAT and LCAT Level descriptions
    @GetMapping("/positions/{lcatDescription}/{lcatLevelDescription}")
    public Integer getPositionIDByLCATAndLevelDescriptions(@PathVariable("lcatDescription") String lcatDescription, @PathVariable("lcatLevelDescription") String lcatLevelDescription)
    {
        Integer positionID = positionRepository.findPositionIDByLCATAndLevelDescription(lcatDescription, lcatLevelDescription);

        if (positionID == null)
        {
            throw new ResourceNotFoundException("Position ID does not exist with LCAT Description :" + lcatDescription + " and LCAT Level Description: " + lcatLevelDescription);
        }

        return positionID;
    }

    // add Position
    @PostMapping("/positions")
    public Position addPosition(@RequestBody Position position)
    {
        return positionRepository.save(position);
    }
}