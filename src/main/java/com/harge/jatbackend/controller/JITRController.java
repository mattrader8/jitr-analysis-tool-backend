package com.harge.jatbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITR;
import com.harge.jatbackend.repository.JITRRepository;

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
public class JITRController 
{
    @Autowired
    private JITRRepository jitrRepository;

    // get all JITRs
    @GetMapping("/jitrs")
    public List<JITR> getAllJiTRs()
    {
        return jitrRepository.findAll();
    }

    // add JITR
    @PostMapping("/jitrs")
    public JITR addJITR(@RequestBody JITR jitr)
    {
        return jitrRepository.save(jitr);
    }

    // get JITR by number
    @GetMapping("/jitrs/{jitrNumber}")
    public ResponseEntity<JITR> getJITRByNumber(@PathVariable int jitrNumber)
    {
        JITR jitr = jitrRepository.findById(jitrNumber)
            .orElseThrow(() -> new ResourceNotFoundException("JITR does not exist with number :" + jitrNumber));
        return ResponseEntity.ok(jitr);
    }

    // update JITR
    @PutMapping("/jitrs/{jitrNumber}")
    public ResponseEntity<JITR> updateJITR(@PathVariable int jitrNumber, @RequestBody JITR jitrDetails)
    {
        JITR jitr = jitrRepository.findById(jitrNumber)
            .orElseThrow(() -> new ResourceNotFoundException("JITR does not exist with id :" + jitrNumber));
        jitr.setJitrDate(jitrDetails.getJitrDate());
        jitr.setNumberOfFTE(jitrDetails.getNumberOfFTE());
        jitr.setJitrStatus(jitrDetails.getJitrStatus());
        jitr.setJitrRating(jitrDetails.getJitrRating());
        jitr.setPraxisEstimatedCost(jitrDetails.getPraxisEstimatedCost());
        jitr.setWinningPrimeEstimatedCost(jitrDetails.getWinningPrimeEstimatedCost());
        jitr.setJitrOrganization(jitrDetails.getJitrOrganization());

        JITR updatedJitr = jitrRepository.save(jitr);

        return ResponseEntity.ok(updatedJitr);
    }

    // delete JITR
    @DeleteMapping("/jitrs/{jitrNumber}")
    public ResponseEntity<Map<String, Boolean>> deleteJITR(@PathVariable int jitrNumber)
    {
        JITR jitr = jitrRepository.findById(jitrNumber)
            .orElseThrow(() -> new ResourceNotFoundException("JITR does not exist with JITR Number :" + jitrNumber));
        
        jitrRepository.delete(jitr);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // get Declined JITRs
    @GetMapping("/jitrs/declined-jitrs")
    public List<JITR> getDeclinedJITRs()
    {
        return jitrRepository.findDeclinedJITRs();
    }

    // get Declined JITRs
    @GetMapping("/jitrs/average-cost-difference")
    public double getAverageCostDifference()
    {
        return jitrRepository.getAverageCostDifference();
    }
}