package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.model.LCAT;
import com.harge.jatbackend.repository.LCATRepository;

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
public class LCATController 
{
    @Autowired
    private LCATRepository lcatRepository;

    // get all LCATs
    @GetMapping("/lcats")
    public List<LCAT> getAllLCATs()
    {
        return lcatRepository.findAll();
    }

    // add LCAT
    @PostMapping("/lcats")
    public LCAT addLCAT(@RequestBody LCAT lcat)
    {
        return lcatRepository.save(lcat);
    }
}