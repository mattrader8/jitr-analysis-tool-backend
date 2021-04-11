package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITRLCATLevels;
import com.harge.jatbackend.repository.JITRLCATLevelsRepository;

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
public class JITRLCATLevelsController 
{
    @Autowired
    private JITRLCATLevelsRepository jitrLCATLevelsRepository;

    // get all JITR LCAT Levels
    @GetMapping("/jitr-lcat-levels")
    public List<JITRLCATLevels> getAllJiTRLCATs()
    {
        return jitrLCATLevelsRepository.findAll();
    }

    // get max JITR LCAT Level ID
    @GetMapping("/jitr-lcat-levels/max-id")
    public long getMaxJITRLCATLevelID()
    {
        return jitrLCATLevelsRepository.getMaxJITRLCATLevelID();
    }

    // get JITR LCAT Levels by JITR number
    @GetMapping("/jitr-lcat-levels/{jitrNumber}")
    public List<JITRLCATLevels> getJITRByNumber(@PathVariable int jitrNumber)
    {
        List<JITRLCATLevels> jitrLcatLevels = null;
        
        try
        { 
            jitrLcatLevels = jitrLCATLevelsRepository.findByJITRNumber(jitrNumber);
        }

        catch (ResourceNotFoundException ex)
        {
            ex.getMessage();
        }

        return jitrLcatLevels;
    }

    // add JITR LCAT Level
    @PostMapping("/jitr-lcat-levels")
    public JITRLCATLevels addJITRLCATLevels(@RequestBody JITRLCATLevels jitrLCATLevels)
    {
        return jitrLCATLevelsRepository.save(jitrLCATLevels);
    }
}