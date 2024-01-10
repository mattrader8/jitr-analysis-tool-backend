package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITR;
import com.harge.jatbackend.model.JITROrganization;
import com.harge.jatbackend.model.JITRPositions;
import com.harge.jatbackend.model.JITRRating;
import com.harge.jatbackend.model.JITRStatus;
import com.harge.jatbackend.model.Position;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JITRPositionsServiceTest 
{
    @Autowired
    private JITRPositionsService jitrPositionsService;

    @Autowired
    private JITRService jitrService;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    void findAllPositions()
    {
        List<JITRPositions> jitrPositionsList = jitrPositionsService.findAllJITRPositions();

        assertFalse(jitrPositionsList.isEmpty());
    }

    @Test
    void findJITRPositionsByJITRNumberWithValidJITRNumber()
    {
        int jitrNumber = 100;

        List<JITRPositions> jitrPositionsList = jitrPositionsService.findJITRPositionsByJITRNumber(jitrNumber);

        if (jitrPositionsList.isEmpty())
        {
            assertTrue(jitrPositionsList.isEmpty());
        }

        else
        {
            assertFalse(jitrPositionsList.isEmpty());
        }
    }

    @Test
    void findJITRPositionsByJITRNumberwithInvalidJITRNumber()
    {
        int jitrNumber = -100;

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            jitrPositionsService.findJITRPositionsByJITRNumber(jitrNumber);
        });

        String expectedMessage = "JITR does not exist with JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    void saveJITRPosition()
    {
        // create JITR Position ID
        String jitrPositionID = UUID.randomUUID().toString();

        // create JITR
        JITR jitr = new JITR();

        JITRStatus jitrStatus = new JITRStatus();
        jitrStatus.setJitrStatusID(10000);
        jitrStatus.setStatusDescription("Awarded");

        JITRRating jitrRating = new JITRRating();
        jitrRating.setJitrRatingID(10000);
        jitrRating.setRatingDescription("Exceptional");

        JITROrganization jitrOrganization = new JITROrganization();
        jitrOrganization.setJitrOrganizationID(10000);
        jitrOrganization.setJitrOrganizationName("Org1");
        
        jitr.setJitrNumber(8);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0.0);
        jitr.setWinningPrimeEstimatedCost(0.0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);
        
        // create Position
        Position position = new Position();
        
        position.setPositionID(11377);
        position.setLcatDescription("Systems Engineer");
        position.setLcatLevelDescription("Expert");

        // create JITR Position
        JITRPositions jitrPosition = new JITRPositions();

        jitrPosition.setJitrPositionID(jitrPositionID);
        jitrPosition.setJitr(jitr);
        jitrPosition.setPosition(position);

        jitrPositionsService.saveJITRPosition(jitrPosition);
    }

    /* --- TESTS FOR UPDATE REQUEST METHODS --- */

    @Test
    void updateJITRPosition()
    {
        // create JITR Position ID
        String jitrPositionID = UUID.randomUUID().toString();

        // create JITR
        JITR jitr = new JITR();

        JITRStatus jitrStatus = new JITRStatus();
        jitrStatus.setJitrStatusID(10000);
        jitrStatus.setStatusDescription("Awarded");

        JITRRating jitrRating = new JITRRating();
        jitrRating.setJitrRatingID(10000);
        jitrRating.setRatingDescription("Exceptional");

        JITROrganization jitrOrganization = new JITROrganization();
        jitrOrganization.setJitrOrganizationID(10000);
        jitrOrganization.setJitrOrganizationName("Org1");
        
        jitr.setJitrNumber(8);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0.0);
        jitr.setWinningPrimeEstimatedCost(0.0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);
        
        // create Position
        Position position = new Position();
        
        position.setPositionID(11377);
        position.setLcatDescription("Systems Engineer");
        position.setLcatLevelDescription("Expert");

        // create JITR Position
        JITRPositions jitrPosition = new JITRPositions();

        jitrPosition.setJitrPositionID(jitrPositionID);
        jitrPosition.setJitr(jitr);
        jitrPosition.setPosition(position);

        jitrPositionsService.saveJITRPosition(jitrPosition);

        // update Position
        position.setPositionID(11339);
        position.setLcatDescription("Software Engineer");
        position.setLcatLevelDescription("Subject Matter Expert");

        jitrPosition.setPosition(position);

        // update JITR Position
        jitrPositionsService.updateJITRPosition(jitrPosition.getJitrPositionID(), jitrPosition);
    }

    /* --- TESTS FOR DELETE REQUEST METHODS --- */

    @Test
    void deleteJITRPosition()
    {
        // create JITR Position ID
        String jitrPositionID = UUID.randomUUID().toString();

        // create JITR
        JITR jitr = new JITR();

        JITRStatus jitrStatus = new JITRStatus();
        jitrStatus.setJitrStatusID(10000);
        jitrStatus.setStatusDescription("Awarded");

        JITRRating jitrRating = new JITRRating();
        jitrRating.setJitrRatingID(10000);
        jitrRating.setRatingDescription("Exceptional");

        JITROrganization jitrOrganization = new JITROrganization();
        jitrOrganization.setJitrOrganizationID(10000);
        jitrOrganization.setJitrOrganizationName("Org1");
        
        jitr.setJitrNumber(8);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0.0);
        jitr.setWinningPrimeEstimatedCost(0.0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);
        
        // create Position
        Position position = new Position();
        
        position.setPositionID(11377);
        position.setLcatDescription("Systems Engineer");
        position.setLcatLevelDescription("Expert");

        // create JITR Position
        JITRPositions jitrPosition = new JITRPositions();

        jitrPosition.setJitrPositionID(jitrPositionID);
        jitrPosition.setJitr(jitr);
        jitrPosition.setPosition(position);

        jitrPositionsService.saveJITRPosition(jitrPosition);

        jitrPositionsService.deleteJITRPosition(jitrPositionID);
    }
}