package com.harge.jatbackend.repository;

import java.util.List;

import com.harge.jatbackend.model.Position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PositionRepository extends JpaRepository<Position, Long>
{
    @Query(value = "SELECT DISTINCT V_LCATDescription_PO FROM position", nativeQuery = true)
    List<String> findLCATDescriptions();

    @Query(value = "SELECT V_LCATLevelDescription_PO FROM position WHERE V_LCATDescription_PO = :lcatDescription", nativeQuery = true)
    List<String> findLCATLevels(@Param("lcatDescription") Object lcatDescription);

    @Query(value = "SELECT C_PositionID_PO FROM position WHERE V_LCATDescription_PO = :lcatDescription AND V_LCATLevelDescription_PO = :lcatLevelDescription", nativeQuery = true)
    int findPositionIDByLCATAndLevelDescription(@Param("lcatDescription") String lcatDescription, @Param("lcatLevelDescription") String lcatLevelDescription);
}