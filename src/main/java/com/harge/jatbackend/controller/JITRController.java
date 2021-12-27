package com.harge.jatbackend.controller;

import java.util.List;
import java.util.Map;

import com.harge.jatbackend.model.JITR;
import com.harge.jatbackend.service.JITRService;

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
    private JITRService jitrService;

    // get all JITRs
    @GetMapping("/jitrs")
    public List<JITR> getAllJITRs()
    {
        return jitrService.findAllJITRs();
    }

    // add JITR
    @PostMapping("/jitrs")
    public JITR addJITR(@RequestBody JITR jitr)
    {
        return jitrService.saveJITR(jitr);
    }

    // get JITR by JITR Number
    @GetMapping("/jitrs/{jitrNumber}")
    public ResponseEntity<JITR> getJITRByJITRNumber(@PathVariable int jitrNumber)
    {
        return jitrService.findJITRByJITRNumber(jitrNumber);
    }

    // update JITR
    @PutMapping("/jitrs/{jitrNumber}")
    public ResponseEntity<JITR> updateJITR(@PathVariable int jitrNumber, @RequestBody JITR jitrDetails)
    {
        return jitrService.updateJITR(jitrNumber, jitrDetails);
    }

    // delete JITR
    @DeleteMapping("/jitrs/{jitrNumber}")
    public ResponseEntity<Map<String, Boolean>> deleteJITR(@PathVariable int jitrNumber)
    {
        return jitrService.deleteJITR(jitrNumber);
    }

    // get Declined JITRs (excludes JITRs without Praxis/Winning Prime costs)
    @GetMapping("/jitrs/declined-jitrs")
    public List<JITR> getDeclinedJITRs()
    {
        return jitrService.findDeclinedJITRs();
    }

    // get Average Cost Difference
    @GetMapping("/jitrs/average-cost-difference")
    public double getAverageCostDifference()
    {
        return jitrService.findAverageCostDifference();
    }
}