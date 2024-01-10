package com.harge.jatbackend.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.Position;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PositionControllerTest 
{
    @Autowired
    private PositionController positionController;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    void getAllPositions()
    {
        List<Position> positionList = positionController.getAllPositions();

        assertFalse(positionList.isEmpty());
    }

    @Test
    void getDistinctLCATDescriptions()
    {
        List<String> lcatDescriptionList = positionController.getDistinctLCATDescriptions();

        assertFalse(lcatDescriptionList.isEmpty());
    }

    @Test
    void getLCATLevelsByValidLCATDescriptionForCancelledJITRs()
    {
        String lcatDescription = "Systems Engineer";

        positionController.getLCATLevelsByLCATDescriptionForCancelledJITRs(lcatDescription);
    }

    @Test
    void getLCATLevelsByInvalidLCATDescriptionForCancelledJITRs()
    {
        String lcatDescription = "System Engineer";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            positionController.getLCATLevelsByLCATDescriptionForCancelledJITRs(lcatDescription);
        });

        String expectedMessage = "Position does not exist with LCAT Description";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getLCATLevelsByValidLCATDescriptionForActiveJITRs()
    {
        String lcatDescription = "Systems Engineer";

        positionController.getLCATLevelsByLCATDescriptionForActiveJITRs(lcatDescription);
    }

    @Test
    void getLCATLevelsByInvalidLCATDescriptionForActiveJITRs()
    {
        String lcatDescription = "System Engineer";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            positionController.getLCATLevelsByLCATDescriptionForActiveJITRs(lcatDescription);
        });

        String expectedMessage = "Position does not exist with LCAT Description";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getPositionIDByLCATAndLevelDescriptionsWithValidInputs()
    {
        String lcatDescription = "Systems Engineer";
        String lcatLevelDescription = "Expert";

        positionController.getPositionIDByLCATAndLevelDescriptions(lcatDescription, lcatLevelDescription);
    }

    @Test
    void getPositionIDByLCATAndLevelDescriptionsWithInvalidLCATDescription()
    {
        String lcatDescription = "System Engineer";
        String lcatLevelDescription = "Expert";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            positionController.getPositionIDByLCATAndLevelDescriptions(lcatDescription, lcatLevelDescription);
        });

        String expectedMessage = "Position ID does not exist with";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getPositionIDByLCATAndLevelDescriptionsWithInvalidLCATLevelDescription()
    {
        String lcatDescription = "Systems Engineer";
        String lcatLevelDescription = "Experrr";

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            positionController.getPositionIDByLCATAndLevelDescriptions(lcatDescription, lcatLevelDescription);
        });

        String expectedMessage = "Position ID does not exist with";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    void addPosition()
    {
        Position position = new Position();

        position.setLcatDescription("Test");
        position.setLcatLevelDescription("Test");

        positionController.addPosition(position);
    }
}