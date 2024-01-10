package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import com.harge.jatbackend.model.JITROrganization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JITROrganizationServiceTest 
{
    @Autowired
    private JITROrganizationService jitrOrganizationService;

    /* --- TETS FOR GET REQUEST METHODS --- */

    @Test
    void findAllJITROrganizations()
    {
        List<JITROrganization> jitrOrganizationList = jitrOrganizationService.findAllJITROrganizations();

        assertFalse(jitrOrganizationList.isEmpty());
    }
}