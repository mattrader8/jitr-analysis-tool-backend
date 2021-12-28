package com.harge.jatbackend.service;

import java.util.List;

import com.harge.jatbackend.model.JITROrganization;
import com.harge.jatbackend.repository.JITROrganizationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JITROrganizationService 
{
    @Autowired
    JITROrganizationRepository jitrOrganizationRepository;

    public List<JITROrganization> findAllJITROrganizations()
    {
        return jitrOrganizationRepository.findAll();
    }
}