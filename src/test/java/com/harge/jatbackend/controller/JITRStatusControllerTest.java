package com.harge.jatbackend.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import com.harge.jatbackend.model.JITRStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JITRStatusControllerTest 
{
    @Autowired
    private JITRStatusController jitrStatusController;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    void getAllJITRStatuses()
    {
        List<JITRStatus> jitrStatusList = jitrStatusController.getAllJITRStatuses();

        assertFalse(jitrStatusList.isEmpty());
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    void addJITRStatus()
    {
        JITRStatus jitrStatus = new JITRStatus();
        jitrStatus.setStatusDescription("Test");

        jitrStatusController.addJITRStatus(jitrStatus);
    }
}