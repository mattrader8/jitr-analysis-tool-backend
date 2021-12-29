package com.harge.jatbackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITRPositions;
import com.harge.jatbackend.repository.JITRPositionsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JITRPositionsService 
{
    @Autowired
    private JITRPositionsRepository jitrPositionsRepository;

    // find all JITR Positions
    public List<JITRPositions> findAllJITRPositions()
    {
        return jitrPositionsRepository.findAll();
    }

    // find JITR Positions by JITR number
    public List<JITRPositions> findJITRPositionsByJITRNumber(int jitrNumber)
    {
        List<JITRPositions> jitrPositionsList = null;

        Integer jitrNumberFromDatabase = jitrPositionsRepository.checkJITRExistsByJITRNumber(jitrNumber);

        if (jitrNumberFromDatabase == null)
        {
            throw new ResourceNotFoundException("JITR does not exist with JITR Number :" + jitrNumber);
        }

        else
        {
            jitrPositionsList = jitrPositionsRepository.findJITRByJITRNumber(jitrNumber);
        }

        return jitrPositionsList;
    }

    // save JITR Position
    public JITRPositions saveJITRPosition(JITRPositions jitrPositions)
    {
        return jitrPositionsRepository.save(jitrPositions);
    }

    // update JITR Position
    public ResponseEntity<JITRPositions> updateJITRPosition(String jitrPositionID, JITRPositions jitrPositionDetails)
    {
        JITRPositions jitrPosition = jitrPositionsRepository.findById(jitrPositionID)
            .orElseThrow(() -> new ResourceNotFoundException("JITR Position does not exist with JITR Position ID :" + jitrPositionID));
        jitrPosition.setPosition(jitrPositionDetails.getPosition());

        JITRPositions updatedJitrPosition = jitrPositionsRepository.save(jitrPosition);

        return ResponseEntity.ok(updatedJitrPosition);
    }

    // delete JITR Position
    public ResponseEntity<Map<String, Boolean>> deleteJITRPosition(String jitrPositionID)
    {
        JITRPositions jitrPosition = jitrPositionsRepository.findById(jitrPositionID)
            .orElseThrow(() -> new ResourceNotFoundException("JITR does not exist with JITR Position ID :" + jitrPositionID));
        
        jitrPositionsRepository.delete(jitrPosition);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}