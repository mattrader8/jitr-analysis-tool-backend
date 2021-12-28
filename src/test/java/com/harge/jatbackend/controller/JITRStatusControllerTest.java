package com.harge.jatbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.harge.jatbackend.model.JITRStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JITRStatusControllerTest 
{
    @Autowired
    private JITRStatusController jitrStatusController;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    public void testGetAllJITRStatuses()
    {
        List<JITRStatus> jitrStatusList = jitrStatusController.getAllJITRStatuses();

        assertFalse(jitrStatusList.isEmpty());
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    public void testAddJITRStatus()
    {
        JITRStatus jitrStatus = new JITRStatus();
        jitrStatus.setStatusDescription("Test");

        jitrStatusController.addJITRStatus(jitrStatus);
    }
}