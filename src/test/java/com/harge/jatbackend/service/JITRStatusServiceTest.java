package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import com.harge.jatbackend.model.JITRStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JITRStatusServiceTest 
{
    @Autowired
    private JITRStatusService jitrStatusService;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    void findAllJITRStatuses()
    {
        List<JITRStatus> jitrStatusList = jitrStatusService.findAllJITRStatuses();

        assertFalse(jitrStatusList.isEmpty());
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    void saveJITRStatus()
    {
        JITRStatus jitrStatus = new JITRStatus();
        jitrStatus.setStatusDescription("Test");

        jitrStatusService.saveJITRStatus(jitrStatus);
    }
}