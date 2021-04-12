package com.harge.jatbackend.repository;

import com.harge.jatbackend.model.Position;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long>
{
    
}