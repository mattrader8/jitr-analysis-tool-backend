package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.harge.jatbackend.model.JITROrganization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JITROrganizationServiceTest 
{
    @Autowired
    private JITROrganizationService jitrOrganizationService;

    /* --- TETS FOR GET REQUEST METHODS --- */

    @Test
    public void testFindAllJITROrganizations()
    {
        List<JITROrganization> jitrOrganizationList = jitrOrganizationService.findAllJITROrganizations();

        assertFalse(jitrOrganizationList.isEmpty());
    }
}