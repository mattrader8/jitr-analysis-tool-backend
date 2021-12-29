package com.harge.jatbackend.controller;

import java.util.List;
import java.util.Map;

import com.harge.jatbackend.model.JITRPositions;
import com.harge.jatbackend.service.JITRPositionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private JITRPositionsService jitrPositionsService;

    // get all JITR Positions
    @GetMapping("/jitr-positions")
    public List<JITRPositions> getAllJITRPositions()
    {
        return jitrPositionsService.findAllJITRPositions();
    }

    // get JITR Positions by JITR number
    @GetMapping("/jitr-positions/{jitrNumber}")
    public List<JITRPositions> getJITRPositionsByJITRNumber(@PathVariable int jitrNumber)
    {
        return jitrPositionsService.findJITRPositionsByJITRNumber(jitrNumber);
    }

    // add JITR Position
    @PostMapping("/jitr-positions")
    public JITRPositions addJITRPositions(@RequestBody JITRPositions jitrPositions)
    {
        return jitrPositionsService.saveJITRPosition(jitrPositions);
    }

    // update JITR Position
    @PutMapping("/jitr-positions/{jitrPositionID}")
    public ResponseEntity<JITRPositions> updateJITRPosition(@PathVariable String jitrPositionID, @RequestBody JITRPositions jitrPositionDetails)
    {
        return jitrPositionsService.updateJITRPosition(jitrPositionID, jitrPositionDetails);
    }

    // delete JITR Position
    @DeleteMapping("/jitr-positions/{jitrPositionID}")
    public ResponseEntity<Map<String, Boolean>> deleteJITRPosition(@PathVariable String jitrPositionID)
    {
        return jitrPositionsService.deleteJITRPosition(jitrPositionID);
    }
}