package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITR;
import com.harge.jatbackend.repository.JITRRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            .orElseThrow(() -> new ResourceNotFoundException("Jitr does not exist with number :" + jitrNumber));
        return ResponseEntity.ok(jitr);
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