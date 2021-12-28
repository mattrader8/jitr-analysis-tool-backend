package com.harge.jatbackend.repository;

import com.harge.jatbackend.model.JITR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JITRRepository extends JpaRepository<JITR, Integer>
{
    @Query(value = "SELECT * FROM jitrs JOIN jitr_status WHERE C_StatusID_JI = C_StatusID_ST AND V_StatusDescription_ST = 'Declined' AND DE_PraxisEstimatedCost_JI <> 0 AND DE_WinningPrimeEstimatedCost_JI <> 0;", nativeQuery = true)
    List<JITR> findDeclinedJITRs();

    @Query(value = "SELECT ROUND(AVG(DE_PraxisEstimatedCost_JI - DE_WinningPrimeEstimatedCost_JI), 2) FROM jitrs JOIN jitr_status WHERE C_StatusID_JI = C_StatusID_ST AND V_StatusDescription_ST = 'Declined' AND DE_PraxisEstimatedCost_JI <> 0 AND DE_WinningPrimeEstimatedCost_JI <> 0;", nativeQuery = true)
    double findAverageCostDifference();
}