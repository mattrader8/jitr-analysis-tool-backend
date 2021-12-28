package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.model.JITROrganization;
import com.harge.jatbackend.service.JITROrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200") 
public class JITROrganizationController 
{
    @Autowired
    private JITROrganizationService jitrOrganizationService;

    // get all JITR Organizations
    @GetMapping("/jitr-organizations")
    public List<JITROrganization> getAllJITROrganizations()
    {
        return jitrOrganizationService.findAllJITROrganizations();
    }
}