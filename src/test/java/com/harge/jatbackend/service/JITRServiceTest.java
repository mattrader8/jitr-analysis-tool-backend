package com.harge.jatbackend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import com.harge.jatbackend.exception.ApiException;
import com.harge.jatbackend.exception.ResourceNotFoundException;
import com.harge.jatbackend.model.JITR;
import com.harge.jatbackend.model.JITROrganization;
import com.harge.jatbackend.model.JITRRating;
import com.harge.jatbackend.model.JITRStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class JITRServiceTest 
{
    @Autowired
    private JITRService jitrService;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    public void testFindAllJITRs()
    {
        List<JITR> jitrList = jitrService.findAllJITRs();

        // when no JITRs exist
        if (jitrList.isEmpty())
        {
            assertTrue(jitrList.isEmpty());
        }

        // when JITRs exist
        else
        {
            assertFalse(jitrList.isEmpty());
        }
    }

    @Test
    public void testFindJITRByJITRNumberWithExistingJITRNumber()
    {
        int jitrNumber = 100;

        ResponseEntity<JITR> jitr = jitrService.findJITRByJITRNumber(jitrNumber);
        int statusCode = jitr.getStatusCodeValue();

        assertEquals(200, statusCode);
    }

    @Test
    public void testFindJITRByJITRNumberWithNonExistingJITRNumber()
    {
        int jitrNumber = 10001;

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            jitrService.findJITRByJITRNumber(jitrNumber);
        });

        String expectedMessage = "JITR does not exist with JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFindDeclinedJITRs()
    {
        List<JITR> jitrList = jitrService.findDeclinedJITRs();

        // when no "Declined" JITRs exist
        if (jitrList.isEmpty())
        {
            assertTrue(jitrList.isEmpty());
        }

        // when "Declined" JITRs exist (excludes "Declined" JITRs where estimated costs = 0)
        else
        {
            assertFalse(jitrList.isEmpty());
        }
    }

    @Test
    public void testFindAverageCostDifference()
    {
        List<JITR> jitrList = jitrService.findDeclinedJITRs();

        if (jitrList.isEmpty())
        {
            assertTrue(jitrList.isEmpty());
        }

        else
        {
            jitrService.findAverageCostDifference();
        }
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    public void testSaveJITRWithValidInputs()
    {
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

        jitr.setJitrNumber(10);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);
    }

    @Test()
    public void testSaveJITRWithInvalidJITRNumber()
    {
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

        jitr.setJitrNumber(-10);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrService.saveJITR(jitr);
        });

        String expectedMessage = "Invalid JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    public void testSaveJITRWithInvalidNumberOfFTE()
    {
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

        jitr.setJitrNumber(10);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(-2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrService.saveJITR(jitr);
        });

        String expectedMessage = "Invalid Number of FTE";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    public void testSaveJITRWithInvalidPraxisEstimaedCost()
    {
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

        jitr.setJitrNumber(10);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(-100.00);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrService.saveJITR(jitr);
        });

        String expectedMessage = "Invalid Praxis Estimated Cost";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    public void testSaveJITRWithInvalidWinningPrimeEstimaedCost()
    {
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

        jitr.setJitrNumber(10);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(-100.00);
        jitr.setJitrOrganization(jitrOrganization);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrService.saveJITR(jitr);
        });

        String expectedMessage = "Invalid Winning Prime Estimated Cost";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /* --- TESTS FOR UPDATE REQUEST METHODS --- */

    @Test
    public void testUpdateJITRWithExistingJITRNumber()
    {
        // first, create new JITR
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

        jitr.setJitrNumber(5);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);

        // update JITR 
        jitr.setNumberOfFTE(3);

        jitrService.updateJITR(jitr.getJitrNumber(), jitr);
    }

    @Test
    public void testUpdateJITRWithNonExistingJITRNumber()
    {
        // first, create new JITR
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

        jitr.setJitrNumber(1);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);

        // update JITR 
        jitr.setNumberOfFTE(3);

        // passing in non-existing JITR Number
        int nonExistingJITRNumber = 10001;

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            jitrService.updateJITR(nonExistingJITRNumber, jitr);
        });

        String expectedMessage = "JITR does not exist with JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUpdateJITRWithInvalidNumberOfFTE()
    {
        // first, create new JITR
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

        jitr.setJitrNumber(2);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);

        // update JITR 
        jitr.setNumberOfFTE(-2);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrService.updateJITR(jitr.getJitrNumber(), jitr);
        });

        String expectedMessage = "Invalid Number of FTE";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUpdateJITRWithInvalidPraxisEstimatedCost()
    {
        // first, create new JITR
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

        jitr.setJitrNumber(3);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);

        // update JITR 
        jitr.setPraxisEstimatedCost(-100.0);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrService.updateJITR(jitr.getJitrNumber(), jitr);
        });

        String expectedMessage = "Invalid Praxis Estimated Cost";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUpdateJITRWithInvalidWinningPrimeEstimatedCost()
    {
        // first, create new JITR
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

        jitr.setJitrNumber(6);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);

        // update JITR 
        jitr.setWinningPrimeEstimatedCost(-100.0);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrService.updateJITR(jitr.getJitrNumber(), jitr);
        });

        String expectedMessage = "Invalid Winning Prime Estimated Cost";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /* --- TESTS FOR DELETE REQUEST METHODS --- */

    @Test
    public void testDeleteJITRWithExistingJITRNumber()
    {
        // first, create new JITR
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

        jitr.setJitrNumber(7);
        jitr.setJitrDate(LocalDate.now());
        jitr.setNumberOfFTE(2);
        jitr.setJitrStatus(jitrStatus);
        jitr.setJitrRating(jitrRating);
        jitr.setPraxisEstimatedCost(0);
        jitr.setWinningPrimeEstimatedCost(0);
        jitr.setJitrOrganization(jitrOrganization);

        jitrService.saveJITR(jitr);

        // delete JITR
        jitrService.deleteJITR(jitr.getJitrNumber());
    }

    @Test
    public void testDeleteJITRWithNonExistingJITRNumber()
    {
        int jitrNumber = 10001;

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            jitrService.deleteJITR(jitrNumber);
        });

        String expectedMessage = "JITR does not exist with JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}