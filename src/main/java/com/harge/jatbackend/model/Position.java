package com.harge.jatbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_PositionID_PO", unique = true, nullable = false)
    private long positionID;

    @Column(name = "V_LCATDescription_PO", nullable = false)
    private String lcatDescription;

    @Column(name = "V_LCATLevelDescription_PO", nullable = false)
    private String lcatLevelDescription;

    @OneToMany(mappedBy = "position")
    private List<JITRPositions> jitrPositions;

    public Position()
    {

    }

    public Position(String lcatDescription, String lcatLevelDescription)
    {
        this.lcatDescription = lcatDescription;
        this.lcatLevelDescription = lcatLevelDescription;
    }

    public long getPositionID() 
    {
        return positionID;
    }

    public void setPositionID(long positionID) 
    {
        this.positionID = positionID;
    }

    public String getLcatDescription() 
    {
        return lcatDescription;
    }

    public void setLcatDescription(String lcatDescription) 
    {
        this.lcatDescription = lcatDescription;
    }

    public String getLcatLevelDescription() 
    {
        return lcatLevelDescription;
    }

    public void setLcatLevelDescription(String lcatLevelDescription) 
    {
        this.lcatLevelDescription = lcatLevelDescription;
    }
}