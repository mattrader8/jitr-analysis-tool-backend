package com.harge.jatbackend;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import com.harge.jatbackend.controller.JITRController;
import com.harge.jatbackend.exception.ApiException;
import com.harge.jatbackend.model.JITR;
import com.harge.jatbackend.model.JITROrganization;
import com.harge.jatbackend.model.JITRRating;
import com.harge.jatbackend.model.JITRStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JITRControllerTest 
{
    @Autowired
    private JITRController jitrController;

    @Test
    public void testGetAllJITRs()
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
    public void testAddJITRWithValidInputs()
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
    public void testAddJITRWithInvalidJITRNumber()
    {
        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {
            
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

            jitrController.addJITR(jitr);
        });

        String expectedMessage = "Invalid JITR Number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    public void testAddJITRWithInvalidNumberOfFTE()
    {
        Exception exception = assertThrows(ApiException.InvalidParameter.class, () -> {
            
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

            jitrController.addJITR(jitr);
        });

        String expectedMessage = "Invalid Number of FTE";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}