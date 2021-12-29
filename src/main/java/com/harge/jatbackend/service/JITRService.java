package com.harge.jatbackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harge.jatbackend.exception.ApiException;
import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITR;
import com.harge.jatbackend.repository.JITRRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JITRService 
{
    @Autowired
    JITRRepository jitrRepository;

    // find all JITRs
    public List<JITR> findAllJITRs()
    {
        return jitrRepository.findAll();
    }

    // save JITR
    public JITR saveJITR(JITR jitr)
    {
        if (jitr.getJitrNumber() < 0)
        {
            throw new ApiException.InvalidParameter("Invalid JITR Number.");
        }

        else if (jitr.getNumberOfFTE() < 0)
        {
            throw new ApiException.InvalidParameter("Invalid Number of FTE.");
        }

        else if (jitr.getPraxisEstimatedCost() < 0)
        {
            throw new ApiException.InvalidParameter("Invalid Praxis Estimated Cost.");
        }

        else if (jitr.getWinningPrimeEstimatedCost() < 0)
        {
            throw new ApiException.InvalidParameter("Invalid Winning Prime Estimated Cost.");
        }

        else
        {
            return jitrRepository.save(jitr);
        }
    }

    // find JITR by JITR Number
    public ResponseEntity<JITR> findJITRByJITRNumber(int jitrNumber)
    {
        JITR jitr = jitrRepository.findById(jitrNumber)
            .orElseThrow(() -> new ResourceNotFoundException("JITR does not exist with JITR Number :" + jitrNumber));
        return ResponseEntity.ok(jitr);
    }

    // update JITR
    public ResponseEntity<JITR> updateJITR(int jitrNumber, JITR jitrDetails)
    {
        JITR updatedJitr = null;

        JITR jitr = jitrRepository.findById(jitrNumber)
            .orElseThrow(() -> new ResourceNotFoundException("JITR does not exist with JITR Number :" + jitrNumber));
        jitr.setJitrDate(jitrDetails.getJitrDate());
        jitr.setNumberOfFTE(jitrDetails.getNumberOfFTE());
        jitr.setJitrStatus(jitrDetails.getJitrStatus());
        jitr.setJitrRating(jitrDetails.getJitrRating());
        jitr.setPraxisEstimatedCost(jitrDetails.getPraxisEstimatedCost());
        jitr.setWinningPrimeEstimatedCost(jitrDetails.getWinningPrimeEstimatedCost());
        jitr.setJitrOrganization(jitrDetails.getJitrOrganization());

        if (jitrDetails.getNumberOfFTE() < 0)
        {
            throw new ApiException.InvalidParameter("Invalid Number of FTE.");
        }

        else if (jitrDetails.getPraxisEstimatedCost() < 0)
        {
            throw new ApiException.InvalidParameter("Invalid Praxis Estimated Cost.");
        }

        else if (jitrDetails.getWinningPrimeEstimatedCost() < 0)
        {
            throw new ApiException.InvalidParameter("Invalid Winning Prime Estimated Cost.");
        }

        else 
        {
            updatedJitr = jitrRepository.save(jitr);
        }

        return ResponseEntity.ok(updatedJitr);
    }

    // delete JITR
    public ResponseEntity<Map<String, Boolean>> deleteJITR(int jitrNumber)
    {
        JITR jitr = jitrRepository.findById(jitrNumber)
            .orElseThrow(() -> new ResourceNotFoundException("JITR does not exist with JITR Number :" + jitrNumber));
        
        jitrRepository.delete(jitr);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // find "Declined" JITRs (excludes JITRs without Praxis/Winning Prime costs)
    public List<JITR> findDeclinedJITRs()
    {
        return jitrRepository.findDeclinedJITRs();
    }

    // find Average Cost Difference
    public double findAverageCostDifference()
    {
        List<JITR> jitrList = findDeclinedJITRs();

        if (jitrList.isEmpty())
        {
            return 0.0;
        }

        else
        {
            return jitrRepository.findAverageCostDifference();
        }
    }
}