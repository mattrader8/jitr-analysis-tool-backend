package com.harge.jatbackend.repository;

import java.util.List;

import com.harge.jatbackend.model.JITRLCATs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JITRLCATsRepository extends JpaRepository<JITRLCATs, Long>
{
    @Query(value = "SELECT * FROM jitr_lcats WHERE C_JITRNumber_JL = :jitrNumber", nativeQuery = true)
    List<JITRLCATs> findByJITRNumber(@Param("jitrNumber") int jitrNumber);

    @Query(value = "SELECT MAX(C_JITRLCATID_JL) FROM jitr_lcats", nativeQuery = true)
    long getMaxJITRLCATID();
}