package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITRLCATs;
import com.harge.jatbackend.repository.JITRLCATsRepository;

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
public class JITRLCATsController 
{
    @Autowired
    private JITRLCATsRepository jitrLCATsRepository;

    // get all JITR LCATs
    @GetMapping("/jitr-lcats")
    public List<JITRLCATs> getAllJiTRLCATs()
    {
        return jitrLCATsRepository.findAll();
    }

    // get max JITR LCAT ID
    @GetMapping("/jitr-lcats/max-id")
    public long getMaxJITRLCATID()
    {
        return jitrLCATsRepository.getMaxJITRLCATID();
    }

    // get JITR LCATs by JITR number
    @GetMapping("/jitr-lcats/{jitrNumber}")
    public List<JITRLCATs> getJITRByNumber(@PathVariable int jitrNumber)
    {
        List<JITRLCATs> jitrLcats = null;
        try 
        {
            jitrLcats = jitrLCATsRepository.findByJITRNumber(jitrNumber);
        }
        
        catch (ResourceNotFoundException ex) 
        {
            ex.getMessage();
        }

        return jitrLcats;
    }

    // add JITRLcat
    @PostMapping("/jitr-lcats")
    public JITRLCATs addJITRLCATs(@RequestBody JITRLCATs jitrLCATs)
    {
        return jitrLCATsRepository.save(jitrLCATs);
    }
}