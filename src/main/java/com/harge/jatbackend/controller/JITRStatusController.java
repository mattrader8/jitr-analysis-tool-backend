package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.model.JITRStatus;
import com.harge.jatbackend.service.JITRStatusService;

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
public class JITRStatusController 
{
    @Autowired
    private JITRStatusService jitrStatusService;

    // get all JITR Statuses
    @GetMapping("/jitr-statuses")
    public List<JITRStatus> getAllJiTRStatuses()
    {
        return jitrStatusService.findAllJITRStatuses();
    }

    // add JITR Status
    @PostMapping("/jitr-statuses")
    public JITRStatus addJITRStatus(@RequestBody JITRStatus jitrStatus)
    {
        return jitrStatusService.saveJITRStatus(jitrStatus);
    }
}