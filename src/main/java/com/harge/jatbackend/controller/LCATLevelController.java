package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.model.LCATLevel;
import com.harge.jatbackend.repository.LCATLevelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200") 
public class LCATLevelController 
{
    @Autowired
    private LCATLevelRepository lcatLevelRepository;

    // get all LCAT Levels
    @GetMapping("/lcat-levels")
    public List<LCATLevel> getAllLCATLevels()
    {
        return lcatLevelRepository.findAll();
    }

    // add LCAT Level
    @PostMapping("/lcat-levels")
    public LCATLevel addLCATLevel(@RequestBody LCATLevel lcatLevel)
    {
        return lcatLevelRepository.save(lcatLevel);
    }
}