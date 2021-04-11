package com.harge.jatbackend.repository;

import java.util.List;

import com.harge.jatbackend.model.JITRLCATLevels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JITRLCATLevelsRepository extends JpaRepository<JITRLCATLevels, Long>
{
    @Query(value = "SELECT * FROM jitr_lcat_levels WHERE C_JITRNumber_LJ = :jitrNumber", nativeQuery = true)
    List<JITRLCATLevels> findByJITRNumber(@Param("jitrNumber") int jitrNumber);

    @Query(value = "SELECT MAX(C_JITRLCATLevelID_LJ) FROM jitr_lcat_levels", nativeQuery = true)
    long getMaxJITRLCATLevelID();
}