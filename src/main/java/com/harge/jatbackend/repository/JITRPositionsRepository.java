package com.harge.jatbackend.repository;

import java.util.List;

import com.harge.jatbackend.model.JITRPositions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JITRPositionsRepository extends JpaRepository<JITRPositions, String>
{
    // finds JITR Number in JITR table
    @Query(value = "SELECT C_JITRNumber_JI FROM jitrs WHERE C_JITRNumber_JI = :jitrNumber", nativeQuery = true)
    Integer checkJITRExistsByJITRNumber(@Param("jitrNumber") int jitrNumber);

    // finds JITR Position in JITR Positions table
    @Query(value = "SELECT * FROM jitr_positions WHERE C_JITRNumber_JP = :jitrNumber", nativeQuery = true)
    List<JITRPositions> findByJITRNumber(@Param("jitrNumber") int jitrNumber);
}