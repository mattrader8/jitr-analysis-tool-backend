package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.Position;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PositionServiceTest 
{
    @Autowired
    private PositionService positionService;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    public void testFindAllPositions()
    {
        List<Position> positionList = positionService.findAllPositions();

        assertFalse(positionList.isEmpty());
    }

    @Test
    public void testFindDistinctLCATDescriptions()
    {
        List<String> lcatDescriptionList = positionService.findDistinctLCATDescriptions();

        assertFalse(lcatDescriptionList.isEmpty());
    }

    @Test
    public void testFindLCATLevelsByValidLCATDescriptionForCancelledJITRs()
    {
        String lcatDescription = "Systems Engineer";

        positionService.findLCATLevelsByLCATDescriptionForCancelledJITRs(lcatDescription);
    }

    @Test
    public void testFindLCATLevelsByInvalidLCATDescriptionForCancelledJITRs()
    {
        String lcatDescription = "System Engineer";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            positionService.findLCATLevelsByLCATDescriptionForCancelledJITRs(lcatDescription);
        });

        String expectedMessage = "Position does not exist with LCAT Description";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFindLCATLevelsByValidLCATDescriptionForActiveJITRs()
    {
        String lcatDescription = "Systems Engineer";

        positionService.findLCATLevelsByLCATDescriptionForActiveJITRs(lcatDescription);
    }

    @Test
    public void testFindLCATLevelsByInvalidLCATDescriptionForActiveJITRs()
    {
        String lcatDescription = "System Engineer";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            positionService.findLCATLevelsByLCATDescriptionForActiveJITRs(lcatDescription);
        });

        String expectedMessage = "Position does not exist with LCAT Description";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFindPositionIDByLCATAndLevelDescriptionsWithValidInputs()
    {
        String lcatDescription = "Systems Engineer";
        String lcatLevelDescription = "Expert";

        positionService.findPositionIDByLCATAndLevelDescriptions(lcatDescription, lcatLevelDescription);
    }

    @Test
    public void testFindPositionIDByLCATAndLevelDescriptionsWithInvalidLCATDescription()
    {
        String lcatDescription = "System Engineer";
        String lcatLevelDescription = "Expert";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            positionService.findPositionIDByLCATAndLevelDescriptions(lcatDescription, lcatLevelDescription);
        });

        String expectedMessage = "Position ID does not exist with";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFindPositionIDByLCATAndLevelDescriptionsWithInvalidLCATLevelDescription()
    {
        String lcatDescription = "Systems Engineer";
        String lcatLevelDescription = "Experrr";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            positionService.findPositionIDByLCATAndLevelDescriptions(lcatDescription, lcatLevelDescription);
        });

        String expectedMessage = "Position ID does not exist with";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    public void testSavePosition()
    {
        Position position = new Position();

        position.setLcatDescription("Test");
        position.setLcatLevelDescription("Test");

        positionService.savePosition(position);
    }
}