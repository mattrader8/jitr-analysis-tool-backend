package com.harge.jatbackend.service;

import java.util.List;

import com.harge.jatbackend.model.JITRStatus;
import com.harge.jatbackend.repository.JITRStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JITRStatusService 
{
    @Autowired
    JITRStatusRepository jitrStatusRepository;

    // find all JITR Statuses
    public List<JITRStatus> findAllJITRStatuses()
    {
        return jitrStatusRepository.findAll();
    }

    // save JITR Status
    public JITRStatus saveJITRStatus(JITRStatus jitrStatus)
    {
        return jitrStatusRepository.save(jitrStatus);
    }
}