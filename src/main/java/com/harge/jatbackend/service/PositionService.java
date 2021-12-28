package com.harge.jatbackend.service;

import java.util.List;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.Position;
import com.harge.jatbackend.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService 
{
    @Autowired
    PositionRepository positionRepository;

    // find all Positions
    public List<Position> findAllPositions()
    {
        return positionRepository.findAll();
    }

    // find distinct LCATs
    public List<String> findDistinctLCATDescriptions() 
    {
        return positionRepository.findLCATDescriptions();
    }

    // find LCAT Levels by LCAT Description for cancelled JITRs
    public List<String> findLCATLevelsByLCATDescriptionForCancelledJITRs(String lcatDescription)
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

    // find LCAT Levels by LCAT Description for active JITRs ('Awarded' and 'Declined' JITR Statuses)
    public List<String> findLCATLevelsByLCATDescriptionForActiveJITRs(String lcatDescription)
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

    // find Position by LCAT and LCAT Level descriptions
    public Integer findPositionIDByLCATAndLevelDescriptions(String lcatDescription, String lcatLevelDescription)
    {
        Integer positionID = positionRepository.findPositionIDByLCATAndLevelDescription(lcatDescription, lcatLevelDescription);

        if (positionID == null)
        {
            throw new ResourceNotFoundException("Position ID does not exist with LCAT Description :" + lcatDescription + " and LCAT Level Description: " + lcatLevelDescription);
        }

        return positionID;
    }

    // save Position
    public Position savePosition(Position position)
    {
        return positionRepository.save(position);
    }
}