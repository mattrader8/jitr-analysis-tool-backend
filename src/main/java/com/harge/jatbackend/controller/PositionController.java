package com.harge.jatbackend.controller;

import java.util.List;

import com.harge.jatbackend.model.Position;
import com.harge.jatbackend.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200") 
public class PositionController 
{
    @Autowired
    private PositionRepository positionRepository;

    // get all Positions
    @GetMapping("/positions")
    public List<Position> getAllPositions()
    {
        return positionRepository.findAll();
    }

    // add Position
    @PostMapping("/positions")
    public Position addPosition(@RequestBody Position position)
    {
        return positionRepository.save(position);
    }
}