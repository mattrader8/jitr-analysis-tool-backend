package com.harge.jatbackend.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import com.harge.jatbackend.model.JITROrganization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JITROrganizationControllerTest 
{
    @Autowired
    private JITROrganizationController jitrOrganizationController;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    void getAllJITROrganizations()
    {
        List<JITROrganization> jitrOrganizationList = jitrOrganizationController.getAllJITROrganizations();

        assertFalse(jitrOrganizationList.isEmpty());
    }
}