package com.harge.jatbackend.controller;

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
class JITRControllerTest 
{
    @Autowired
    private JITRController jitrController;

    /* --- TESTS FOR GET REQUEST METHODS --- */

    @Test
    void getAllJITRs()
    {
        List<JITR> jitrList = jitrController.getAllJITRs();

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
    void getJITRByJITRNumberWithExistingJITRNumber()
    {
        int jitrNumber = 100;

        ResponseEntity<JITR> jitr = jitrController.getJITRByJITRNumber(jitrNumber);
        int statusCode = jitr.getStatusCodeValue();

        assertEquals(200, statusCode);
    }

    @Test
    void getJITRByJITRNumberWithNonExistingJITRNumber()
    {
        int jitrNumber = 10001;

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            jitrController.getJITRByJITRNumber(jitrNumber);
        });

        String expectedMessage = "JITR does not exist with JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getDeclinedJITRs()
    {
        List<JITR> jitrList = jitrController.getDeclinedJITRs();

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
    void getAverageCostDifference()
    {
        List<JITR> jitrList = jitrController.getDeclinedJITRs();

        if (jitrList.isEmpty())
        {
            assertTrue(jitrList.isEmpty());
        }

        else
        {
            jitrController.getAverageCostDifference();
        }
    }

    /* --- TESTS FOR POST REQUEST METHODS --- */

    @Test
    void addJITRWithValidInputs()
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

        jitrController.addJITR(jitr);
    }

    @Test()
    void addJITRWithInvalidJITRNumber()
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

            jitrController.addJITR(jitr);
        });

        String expectedMessage = "Invalid JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void addJITRWithInvalidNumberOfFTE()
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

            jitrController.addJITR(jitr);
        });

        String expectedMessage = "Invalid Number of FTE";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void addJITRWithInvalidPraxisEstimaedCost()
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

            jitrController.addJITR(jitr);
        });

        String expectedMessage = "Invalid Praxis Estimated Cost";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    void addJITRWithInvalidWinningPrimeEstimaedCost()
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

            jitrController.addJITR(jitr);
        });

        String expectedMessage = "Invalid Winning Prime Estimated Cost";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /* --- TESTS FOR UPDATE REQUEST METHODS --- */

    @Test
    void updateJITRWithExistingJITRNumber()
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

        jitrController.addJITR(jitr);

        // update JITR 
        jitr.setNumberOfFTE(3);

        jitrController.updateJITR(jitr.getJitrNumber(), jitr);
    }

    @Test
    void updateJITRWithNonExistingJITRNumber()
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

        jitrController.addJITR(jitr);

        // update JITR 
        jitr.setNumberOfFTE(3);

        // passing in non-existing JITR Number
        int nonExistingJITRNumber = 10001;

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            jitrController.updateJITR(nonExistingJITRNumber, jitr);
        });

        String expectedMessage = "JITR does not exist with JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateJITRWithInvalidNumberOfFTE()
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

        jitrController.addJITR(jitr);

        // update JITR 
        jitr.setNumberOfFTE(-2);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrController.updateJITR(jitr.getJitrNumber(), jitr);
        });

        String expectedMessage = "Invalid Number of FTE";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateJITRWithInvalidPraxisEstimatedCost()
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

        jitrController.addJITR(jitr);

        // update JITR 
        jitr.setPraxisEstimatedCost(-100.0);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrController.updateJITR(jitr.getJitrNumber(), jitr);
        });

        String expectedMessage = "Invalid Praxis Estimated Cost";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateJITRWithInvalidWinningPrimeEstimatedCost()
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

        jitrController.addJITR(jitr);

        // update JITR 
        jitr.setWinningPrimeEstimatedCost(-100.0);

        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {

            jitrController.updateJITR(jitr.getJitrNumber(), jitr);
        });

        String expectedMessage = "Invalid Winning Prime Estimated Cost";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /* --- TESTS FOR DELETE REQUEST METHODS --- */

    @Test
    void deleteJITRWithExistingJITRNumber()
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

        jitrController.addJITR(jitr);

        // delete JITR
        jitrController.deleteJITR(jitr.getJitrNumber());
    }

    @Test
    void deleteJITRWithNonExistingJITRNumber()
    {
        int jitrNumber = 10001;

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

            jitrController.deleteJITR(jitrNumber);
        });

        String expectedMessage = "JITR does not exist with JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}