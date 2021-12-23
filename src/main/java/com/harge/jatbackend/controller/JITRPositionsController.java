package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITRPositions;
import com.harge.jatbackend.repository.JITRPositionsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200") 
public class JITRPositionsController 
{
    @Autowired
    private JITRPositionsRepository jitrPositionsRepository;

    // get all JITR Positions
    @GetMapping("/jitr-positions")
    public List<JITRPositions> getAllJITRPositions()
    {
        return jitrPositionsRepository.findAll();
    }

    // get JITR Positions by JITR number
    @GetMapping("/jitr-positions/{jitrNumber}")
    public List<JITRPositions> getJITRByNumber(@PathVariable int jitrNumber)
    {
        List<JITRPositions> jitrPositions = null;
        try 
        {
            jitrPositions = jitrPositionsRepository.findByJITRNumber(jitrNumber);
        }
        
        catch (ResourceNotFoundException ex) 
        {
            ex.getMessage();
        }

        return jitrPositions;
    }

    // add JITR Position
    @PostMapping("/jitr-positions")
    public JITRPositions addJITRPositions(@RequestBody JITRPositions jitrPositions)
    {
        return jitrPositionsRepository.save(jitrPositions);
    }

    // update JITR Position
    @PutMapping("/jitr-positions/{jitrPositionID}")
    public ResponseEntity<JITRPositions> updateJITR(@PathVariable String jitrPositionID, @RequestBody JITRPositions jitrPositionDetails)
    {
        JITRPositions jitrPosition = jitrPositionsRepository.findById(jitrPositionID)
            .orElseThrow(() -> new ResourceNotFoundException("JITR Position does not exist with id :" + jitrPositionID));
        jitrPosition.setPosition(jitrPositionDetails.getPosition());

        JITRPositions updatedJitrPosition = jitrPositionsRepository.save(jitrPosition);

        return ResponseEntity.ok(updatedJitrPosition);
    }

}