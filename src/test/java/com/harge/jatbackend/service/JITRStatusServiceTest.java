package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.harge.jatbackend.model.JITRStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JITRStatusServiceTest 
{
    @Autowired
    private JITRStatusService jitrStatusService;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    public void testFindAllJITRStatuses()
    {
        List<JITRStatus> jitrStatusList = jitrStatusService.findAllJITRStatuses();

        assertFalse(jitrStatusList.isEmpty());
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    public void testSaveJITRStatus()
    {
        JITRStatus jitrStatus = new JITRStatus();
        jitrStatus.setStatusDescription("Test");

        jitrStatusService.saveJITRStatus(jitrStatus);
    }
}