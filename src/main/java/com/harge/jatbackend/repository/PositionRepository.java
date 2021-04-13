package com.harge.jatbackend.repository;

import java.util.List;

import com.harge.jatbackend.model.Position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PositionRepository extends JpaRepository<Position, Long>
{
    @Query(value = "SELECT DISTINCT V_LCATDescription_PO FROM position", nativeQuery = true)
    List<Object> findLCATDescriptions();

    @Query(value = "SELECT V_LCATLevelDescription_PO FROM position WHERE V_LCATDescription_PO = :lcatDescription", nativeQuery = true)
    List<Object> findLCATLevels(@Param("lcatDescription") String lcatDescription);
}