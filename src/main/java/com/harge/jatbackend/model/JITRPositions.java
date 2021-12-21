package com.harge.jatbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jitr_positions")
public class JITRPositions 
{
    @Id
    @Column(name = "C_JITRPositionID_JP", unique = true, nullable = false)
    private String jitrPositionID;

    @ManyToOne()
    @JoinColumn(name = "C_JITRNumber_JP", nullable = false)
    JITR jitr;

    @ManyToOne
    @JoinColumn(name = "C_PositionID_JP", nullable = false)
    Position position;

    public JITRPositions()
    {

    }

    public JITRPositions(JITR jitr, Position position)
    {
        this.jitr = jitr;
        this.position = position;
    }

    public String getJitrPositionID() 
    {
        return jitrPositionID;
    }

    public void setJitrPositionID(String jitrPositionID) 
    {
        this.jitrPositionID = jitrPositionID;
    }

    public JITR getJitr() 
    {
        return jitr;
    }

    public void setJitr(JITR jitr) 
    {
        this.jitr = jitr;
    }

    public Position getPosition() 
    {
        return position;
    }

    public void setPosition(Position position) 
    {
        this.position = position;
    }
}