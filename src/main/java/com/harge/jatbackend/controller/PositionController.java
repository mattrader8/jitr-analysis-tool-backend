package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.model.Position;
import com.harge.jatbackend.service.PositionService;

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
    private PositionService positionService;

    // get all Positions
    @GetMapping("/positions")
    public List<Position> getAllPositions()
    {
        return positionService.findAllPositions();
    }

    // get distinct LCAT Descriptions
    @GetMapping("/positions/lcats")
    public List<String> getDistinctLCATDescriptions() 
    {
        return positionService.findDistinctLCATDescriptions();
    }

    // get LCAT Levels by LCAT Description for "Cancelled" JITRs
    @GetMapping("/positions/cancelled/{lcatDescription}")
    public List<String> getLCATLevelsByLCATDescriptionForCancelledJITRs(@PathVariable String lcatDescription)
    {
        return positionService.findLCATLevelsByLCATDescriptionForCancelledJITRs(lcatDescription);
    }

    // get LCAT Levels by LCAT Description for active JITRs ('Awarded' and 'Declined' JITR Statuses)
    @GetMapping("/positions/{lcatDescription}")
    public List<String> getLCATLevelsByLCATDescriptionForActiveJITRs(@PathVariable String lcatDescription)
    {
        return positionService.findLCATLevelsByLCATDescriptionForActiveJITRs(lcatDescription);
    }

    // get Position by LCAT and LCAT Level descriptions
    @GetMapping("/positions/{lcatDescription}/{lcatLevelDescription}")
    public Integer getPositionIDByLCATAndLevelDescriptions(@PathVariable("lcatDescription") String lcatDescription, @PathVariable("lcatLevelDescription") String lcatLevelDescription)
    {
        return positionService.findPositionIDByLCATAndLevelDescriptions(lcatDescription, lcatLevelDescription);
    }

    // add Position
    @PostMapping("/positions")
    public Position addPosition(@RequestBody Position position)
    {
        return positionService.savePosition(position);
    }
}